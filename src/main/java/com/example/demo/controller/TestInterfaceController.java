package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;

/**
 * @BelongsProject: demo
 * @BelongsPackage: com.example.demo
 * @Author: Mr.wang
 * @Date: 2022/5/27 21:10
 * @Description: 测试接口controller
 */
@RestController
public interface TestInterfaceController {

    public void test();

    public void testMybatis();
}
