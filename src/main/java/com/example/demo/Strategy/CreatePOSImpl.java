package com.example.demo.Strategy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service("POS")
@Slf4j
public class CreatePOSImpl extends AbstractCreate{

    @Override
    public void createOrder() {
        log.info("POS创建订单开始了！");
        common();
        commonP();
        cover();

    }

    @Override
    String cover() {
        log.info("Pos订单的cover覆盖方法执行了");
        return "pos";
    }
}
