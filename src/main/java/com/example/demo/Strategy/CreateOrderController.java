package com.example.demo.Strategy;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author Mr.wang
 */
@RestController
@RequestMapping(value = "/test")
public class CreateOrderController {

    @Resource
    private Map<String,CreateService> createServiceMap;

    @PostMapping(value = "/createPos")
    public String createPos (HttpServletRequest request, String userName){

        createServiceMap.get("POS").createOrder();
        return "POS订单创建成功";
    }
    @PostMapping(value = "/createDG")
    public String createDG (HttpServletRequest request, String userName){
        createServiceMap.get("DG").createOrder();

        return "DG订单创建成功";
    }
}
