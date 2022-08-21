package com.digio.logexercise.service;

import com.digio.logexercise.model.HttpRequestLogEntry;
import com.digio.logexercise.model.HttpRequestLogSummary;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class HttpRequestLogService {

    public HttpRequestLogSummary read(String resourceIdentifier) {
        return parseFile(resourceIdentifier);
    }

    private HttpRequestLogSummary parseFile(String filePath) {

        HttpRequestProcessor httpRequestProcessor = new HttpRequestProcessor();

        try (Stream<String> stream = Files.lines(ResourceUtils.getFile(String.format("classpath:%s", filePath)).toPath())) {

            stream.map(this::parseLogLine).forEach(httpRequestProcessor::add);

        } catch (IOException e) {
            // todo: not ideal, should be custom exception
            throw new RuntimeException("Log file does not exist.");
        }
        return httpRequestProcessor.getSummary();
    }

    private HttpRequestLogEntry parseLogLine(String line) {
        String[] lineDetail = line.split(" ");
        if (lineDetail.length < 7) {
            // todo: not ideal, should be custom exception
            throw new RuntimeException("Invalid log entry.");
        }
        return new HttpRequestLogEntry(lineDetail[0], lineDetail[6]);
    }

    static class HttpRequestProcessor {
        final private int limit = 3;
        final private HashMap<String, Integer> urlMap;
        final private HashMap<String, Integer> ipMap;

        HttpRequestProcessor() {
            urlMap = new HashMap<>();
            ipMap = new HashMap<>();
        }

        public void add(HttpRequestLogEntry logEntry) {
            // todo: some dodgy cleaning, this can be improved:
            String url = logEntry.getUrl();
            if (url.contains("http://example.net")) {
                url = url.substring(18);
            }
            urlMap.merge(url, 1, Integer::sum);

            ipMap.merge(logEntry.getIp(), 1, Integer::sum);
        }

        public HttpRequestLogSummary getSummary() {
            HttpRequestLogSummary httpRequestLogSummary = new HttpRequestLogSummary();
            httpRequestLogSummary.setTopVisitedUrls(findTopEntries(urlMap));
            httpRequestLogSummary.setTopActiveIps(findTopEntries(ipMap));
            httpRequestLogSummary.setAmountUniqueIp(ipMap.size());
            return httpRequestLogSummary;
        }

        private List<String> findTopEntries(Map<String, Integer> map) {
            return map.entrySet().stream()
                    .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                    .limit(limit)
                    .map(Map.Entry::getKey).collect(Collectors.toList());
        }
    }
}
