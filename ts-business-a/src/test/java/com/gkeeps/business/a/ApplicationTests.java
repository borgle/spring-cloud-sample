package com.gkeeps.business.a;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@TestPropertySource(properties = {"business.a.myabc: 123"})
@SpringBootTest
public class ApplicationTests {

    @Test
    public void contextLoads() {
    }

}
