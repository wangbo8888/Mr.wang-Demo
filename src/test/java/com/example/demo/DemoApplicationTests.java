package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Set;

@SpringBootTest
class DemoApplicationTests {
@Resource
private RedisTemplateScan redisTemplateScan;


    @Test
    void contextLoads() {
        Set<String> strings = redisTemplateScan.scanMatch("aa:");
        strings.forEach( System.out::println );
    }

}
