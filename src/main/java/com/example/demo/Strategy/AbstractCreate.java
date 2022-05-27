package com.example.demo.Strategy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
public abstract class AbstractCreate implements CreateService{

    protected void common(){
        log.info("abstract-protected公共方法调用开始了");
    }
    abstract String cover();

    public void commonP(){
        log.info("abstract-publice公共方法调用开始了");
    }

}
