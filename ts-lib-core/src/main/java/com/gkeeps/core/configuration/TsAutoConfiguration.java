package com.gkeeps.core.configuration;

import org.springframework.context.annotation.Configuration;

@Configuration
public class TsAutoConfiguration {

    public TsAutoConfiguration() {
        System.out.println("Load OK.");
    }
}
