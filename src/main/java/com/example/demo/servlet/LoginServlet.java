package com.example.demo.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {

    private HttpServletRequest request;
    private HttpServletResponse response;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.request = request;
        this.response = response;
        response.setContentType("text/html;charset=utf-8");
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        PrintWriter out=response.getWriter();
        //1.把账号、密码分别设置为root、123
        if(username.equals("root")&&password.equals("123")){
            //2.创建User对象保存账号、密码
            UserTest userTest =new UserTest();
            userTest.setUsername(username);
            userTest.setPassword(password);

            //3.创建Session对象保存User对象
            request.getSession().setAttribute("user", userTest);

            //4.登陆成功跳转指定页面
            response.sendRedirect("/Project01/IndexServlet");
        }
        else{
            out.println("账号或密码错误，请从新输入....");
        }
    }

}
