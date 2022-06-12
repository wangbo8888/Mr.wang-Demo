package com.example.demo.ThreadLocalService;


import com.example.demo.servlet.UserTest;
import org.springframework.stereotype.Component;

@Component
public class ThreadLocalTest {

     static ThreadLocal<UserTest> threadLocal = new ThreadLocal();
     public void setThreadLocal(UserTest userTest){

        threadLocal.set(userTest);
//        threadLocal.remove();
    }
    public UserTest getThreadLocal(){
     return threadLocal.get();
    }



}
