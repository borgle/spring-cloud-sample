package com.gkeeps.clients.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class demoService {
    final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "testFallback")
    public String test() {
        String reqURL = "http://business-a/test";
        return restTemplate.getForEntity(reqURL, String.class).getBody();
    }

    public String testFallback() {
        return "[error] " + sdf.format(new Date());
    }

    @HystrixCommand(fallbackMethod = "timeoutFallback")
    public String timeout() {
        String reqURL = "http://business-a/test";
        return restTemplate.getForEntity(reqURL, String.class).getBody();
    }

    public String timeoutFallback() {
        return "[error] timeout " + sdf.format(new Date());
    }

}
