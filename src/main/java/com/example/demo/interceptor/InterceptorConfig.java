package com.example.demo.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author Mr.wang
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Resource
    private LoginInterceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //addPathPatterns指定拦截器要拦截的路径
        //excludePathPatterns指定拦截器不拦截的路径
        registry.addInterceptor(interceptor).addPathPatterns("/**").excludePathPatterns("/test/index");
    }

}
