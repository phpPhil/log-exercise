package com.digio.logexercise.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HttpRequestLogEntry {
    private String ip;
    private String url;

    public HttpRequestLogEntry(String ip, String url) {
        this.ip = ip;
        this.url = url;
    }
}
