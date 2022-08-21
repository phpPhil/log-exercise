package com.digio.logexercise.controller;

import com.digio.logexercise.model.HttpRequestLogSummary;
import com.digio.logexercise.service.HttpRequestLogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
class LogSummary {
    final private HttpRequestLogService httpRequestlogService;

    LogSummary(HttpRequestLogService httpRequestlogService) {
        this.httpRequestlogService = httpRequestlogService;
    }

    @GetMapping("/log/httpRequest/summary/{logId}")
    HttpRequestLogSummary httpRequestLogSummary(@PathVariable("logId") String logId) {
        return httpRequestlogService.read(String.format("log-%s.log",logId));
    }

    // todo: implement exception mapping. Currently runtime exceptions are not handled
}
