package io.borgle.business.a.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoService {

    @Value("${server.port}")
    String port;

    @Value("${business.a.myabc}")
    String myabc;

    final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @RequestMapping("/test")
    public String test() {
        return String.format("[%s] port = %s, myabc = %s",
            sdf.format(new Date()), port, myabc);
    }

    @RequestMapping("/timeout")
    public String timeout() throws Exception {
        Thread.sleep(10 * 1000);
        return String.format("[%s] port = %s, myabc = %s",
            sdf.format(new Date()), port, myabc);
    }
}
