package com.digio.logexercise.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class HttpRequestLogSummary implements Serializable {
    private int amountUniqueIp;
    private List<String> topVisitedUrls;
    private List<String> topActiveIps;
}
