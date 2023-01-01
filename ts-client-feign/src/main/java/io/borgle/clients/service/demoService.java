package io.borgle.clients.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "business-a", fallback = demoService.testFallback.class)
public interface demoService {

    @RequestMapping("/test")
    String test();

    @RequestMapping("/timeout")
    String timeout();

    @Component
    class testFallback implements demoService {
        final SimpleDateFormat sdf = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");

        @Override
        public String test() {
            return "[error] " + sdf.format(new Date());
        }

        @Override
        public String timeout() {
            return "[error] timeout " + sdf.format(new Date());
        }

    }

}
