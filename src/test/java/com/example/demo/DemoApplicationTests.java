package com.example.demo;

import com.example.demo.enums.CloudEnum;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@SpringBootTest
class DemoApplicationTests {
@Resource
private RedisTemplateScan redisTemplateScan;


    @Test
    void contextLoads() {
//        Set<String> strings = redisTemplateScan.scanMatch("aa:");
//        strings.forEach( System.out::println );
        List<CloudEnum> all = CloudEnum.getAll();
        List<CloudEnum> allLamdar = CloudEnum.getAllLamdar();
        System.out.println("1111");
    }

}
