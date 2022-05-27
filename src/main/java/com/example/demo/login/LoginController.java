package com.example.demo.login;

import com.example.demo.ThreadLocalService.ThreadLocalTest;
import com.example.demo.annotation.MyAnnotation;
import com.example.demo.servlet.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
@RestController
@RequestMapping(value = "/test")
@MyAnnotation(value = "v1",color = "yellow",off = false,list = {"1","2"})
public class LoginController {

        @Resource
        private ThreadLocalTest threadLocalTest;

        @PostMapping("/test")
        public String queryAddress(HttpServletRequest httpRequest) {
         httpRequest.getSession();
            return "aaaa";
        }

        @PostMapping(value = "/login{userName}")
        public String login (HttpServletRequest request,String userName){
            User user = threadLocalTest.getThreadLocal();
            String msg="登陆失败！";
//            User user= userRepository.findByUserName(userName);
//            if (user!=null && user.getPassword().equals(password)){
                request.getSession().setAttribute("user",userName);

            String requestURI = request.getRequestURI();
            String requestURL = request.getRequestURL().toString();
            String remoteAddr = request.getRemoteAddr();
            String remoteHost = request.getRemoteHost();
            request.getContextPath();
            msg="登陆成功！"+userName;
            return msg;
        }


    @PostMapping(value = "/index")
    public String index (HttpServletRequest request){
        User user1 = threadLocalTest.getThreadLocal();
        String msg="首页内容";
        Object user= request.getSession().getAttribute("user");

        if (user==null){
            msg="请先登录！";
        }else {
            msg =user.toString();

        }
        return msg+"8089";
    }
}
