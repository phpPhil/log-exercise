package com.digio.logexercise;

import com.digio.logexercise.service.HttpRequestLogService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {

    @Bean
    @Scope(value = "prototype")
    public HttpRequestLogService httpRequestlogService() {
        return new HttpRequestLogService();
    }
}
