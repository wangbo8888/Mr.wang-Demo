package com.example.demo.controller;

import com.example.demo.Strategy.CreateService;
import com.example.demo.servlet.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @BelongsProject: demo
 * @BelongsPackage: com.example.demo.controller
 * @Author: Mr.wang
 * @Date: 2022/5/27 21:11
 * @Description:
 */
@Slf4j
@Service
public class TestInterfaceControllerImpl implements TestInterfaceController {
    @Resource
    private Map<String,CreateService> createServiceMap;

    @Override
    @PostMapping("/test")
    public void test() {
        User user = new User("12","34");
        log.info("本次的用户是{}",user);
        createServiceMap.get("POS").createOrder();
    }
}
