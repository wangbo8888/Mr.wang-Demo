package com.example.demo.chainfilter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @BelongsProject: demo
 * @BelongsPackage: com.example.demo.chainfilter
 * @Author: Mr.wang
 * @Date: 2022/5/22 17:25
 * @Description:
 */
@Service
@Slf4j
public class Handle2 extends CoreHandle{
    @Override
    public void handleRequest(int LeaveDays) {
        if (2== LeaveDays){
            log.info("handle2处理请求");
        }else {
            log.info("请求处理器从2下去了");
            if (null != getNext()){
                log.info("下一个处理器不为空");

//                getNext().handleRequest(LeaveDays);
                doNext(LeaveDays);
            }
            log.info("下一个处理器为空");
        }
        log.info("++++hanadle2 waimianle");
    }
}
