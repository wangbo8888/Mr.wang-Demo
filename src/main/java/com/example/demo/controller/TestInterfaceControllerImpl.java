package com.example.demo.controller;

import com.example.demo.Convert.ClassA;
import com.example.demo.Convert.ClassB;
import com.example.demo.Strategy.CreateService;
import com.example.demo.mybatis.dao.User;
import com.example.demo.mybatis.mapper.UserMapper;
import com.example.demo.mybatis.result.ListVo;
import com.example.demo.servlet.UserTest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    @Resource
    private UserMapper userMapper;


    @Override
    @PostMapping("/test")
    public void test() {
        UserTest userTest = new UserTest("123","34");
        log.info("本次的用户是{}", userTest);
        createServiceMap.get("POS").createOrder();
        List<UserTest> userTestList = new ArrayList<>();
        List<UserTest> userTestList1 = new ArrayList<>();
        ClassA classA = new ClassA();
        ClassB classB = new ClassB();
         userTestList1.stream().map(TestInterfaceControllerImpl::build).collect(Collectors.toList());

    }
    @PostMapping("/testMybatis")
    @Override
    public void testMybatis() {
//        User user = userMapper.queryById(1);
//        System.out.println(user);
        List<ListVo> listVos = userMapper.selectList();
        System.out.println("111");

    }

    public static UserTest build(UserTest userTest){
//        user.setUsername(a.getUsername());
//        user.setPassword(b.getPassword());
        return userTest;
    }
}
