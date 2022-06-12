package com.example.demo.interceptor;

import com.example.demo.ThreadLocalService.ThreadLocalTest;
import com.example.demo.annotation.MyAnnotation;
import com.example.demo.servlet.UserTest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Mr.wang
 */
@Configuration
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Resource
    private ThreadLocalTest threadLocalTest;

     @Override
     public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
         System.out.println("拦截器执行");
         UserTest userTest = new UserTest();
         userTest.setUsername("aaa");
         userTest.setPassword("bbb");
         threadLocalTest.setThreadLocal(userTest);
         HandlerMethod handlerMethod = (HandlerMethod) handler;
         MyAnnotation methodAnnotation = handlerMethod.getMethodAnnotation(MyAnnotation.class);
         if(null == methodAnnotation){
             return true;
         }
         //ThreadLocal
//         User user1 = new User();
//         user.setUsername("aaa1");
//         user.setPassword("bbb1");
//         threadLocalTest.setThreadLocal(user);
//         log.info(request.getSession().toString());
         //resong 写出
//         Map<String,Object> map = new HashMap<>();
//         map.put("state", false);
//         map.put("msg", "Token已经过期!!!");
//         map.put("data","{}");
//         String json = new ObjectMapper().writeValueAsString(map);
//         response.setContentType("application/json;charset=UTF-8");
//         response.getWriter().write(json);
//         response.getWriter().println(json);
        return true;
    }
}
