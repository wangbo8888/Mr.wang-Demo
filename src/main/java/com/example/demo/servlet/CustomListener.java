package com.example.demo.servlet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
@Slf4j
@Configuration
public class CustomListener implements ServletContextListener, ServletRequestListener,
        HttpSessionListener{
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("监听到了{}","contex初始化");
        ServletContextListener.super.contextInitialized(sce);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("监听到了{}","contex销毁");
        ServletContextListener.super.contextDestroyed(sce);
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        log.info("监听到了{}","请求销毁");
        ServletRequestListener.super.requestDestroyed(sre);
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        log.info("监听到了{}","请求初始化");
        HttpServletRequest servletRequest = (HttpServletRequest)sre.getServletRequest();
        log.info(servletRequest.getQueryString());
        ServletRequestListener.super.requestInitialized(sre);
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        log.info("监听到了{}","sessio初始化");
        HttpSessionListener.super.sessionCreated(se);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        log.info("监听到了{}","sessio销毁");
        HttpSessionListener.super.sessionDestroyed(se);
    }
}
