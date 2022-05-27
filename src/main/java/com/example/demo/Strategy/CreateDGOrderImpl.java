package com.example.demo.Strategy;

import com.example.demo.chainfilter.Handle1;
import com.example.demo.chainfilter.Handle2;
import com.example.demo.chainfilter.Handle3;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("DG")
@Slf4j
public class CreateDGOrderImpl extends AbstractCreate{
    @Resource
    private Handle1 handle1;
    @Resource
    private Handle2 handle2;
    @Resource
    private Handle3 handle3;

    @Override
    String cover() {
      log.info("DG的覆盖方法执行了！！！");
//      setNext(null) 无作用
//      handle1.setNext(handle2).setNext(handle3).setNext(null);
        int day = 2;
        handle1.setNext(handle2).setNext(handle3).setNext(null);
      handle1.handleRequest(day);
      return"DG";
    }

    @Override
    public void createOrder() {
        log.info("DG的创建订单开始了");
        common();
        commonP();
        cover();
    }
}
