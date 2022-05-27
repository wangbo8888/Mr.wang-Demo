package com.example.demo.ThreadLocalService;


import com.example.demo.servlet.User;
import org.springframework.stereotype.Component;

@Component
public class ThreadLocalTest {

     static ThreadLocal<User> threadLocal = new ThreadLocal();
     public void setThreadLocal(User user){

        threadLocal.set(user);
//        threadLocal.remove();
    }
    public  User getThreadLocal(){
     return threadLocal.get();
    }



}
