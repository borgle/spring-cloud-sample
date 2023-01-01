package io.borgle.clients.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.borgle.clients.service.demoService;

@RestController
public class demoController {

    @Autowired
    demoService service;

    @RequestMapping("/test")
    public String test() throws Exception {
        return service.test();
    }

    @RequestMapping("/timeout")
    public String timeout() throws Exception {
        return service.timeout();
    }

}
