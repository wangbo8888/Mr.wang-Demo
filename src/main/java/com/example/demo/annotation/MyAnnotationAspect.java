package com.example.demo.annotation;

import com.example.demo.ThreadLocalService.ThreadLocalTest;
import com.example.demo.servlet.User;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Aspect // 1.表明这是一个切面类
@Component
@Slf4j
public class MyAnnotationAspect {
    @Resource
    private ThreadLocalTest threadLocalTest;


        // 2. PointCut表示这是一个切点，@annotation表示这个切点切到一个注解上，后面带该注解的全类名
        // 切面最主要的就是切点，所有的故事都围绕切点发生
        // logPointCut()代表切点名称
        @Pointcut("@annotation(com.example.demo.annotation.MyAnnotation)")
        public void logPointCut(){};

        @Before("@within(myAnnotation)")
        public String before(JoinPoint joinPoint,MyAnnotation myAnnotation){
            User user = threadLocalTest.getThreadLocal();
            log.info("进入aop了+++++++++++++++++++++++++++++++++++++"+myAnnotation.color());
            //获取注解上的方法
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            Method method = methodSignature.getMethod();
            MyAnnotation annotation = method.getAnnotation(MyAnnotation.class);

            return user.toString();

    }

        // 3. 环绕通知
       // @Around("@within(myAnnotation)")
        public void logAround(ProceedingJoinPoint joinPoint, MyAnnotation myAnnotation)throws Exception{
            String color = myAnnotation.color();
            log.info("进入aop了+++++++++++++++++++++++++++++++++++++"+color);
            // 获取方法名称
            String methodName = joinPoint.getSignature().getName();
            // 获取入参
            Object[] param = joinPoint.getArgs();
            StringBuilder sb = new StringBuilder();
            for(Object o : param){
                sb.append(o + "; ");
            }
            System.out.println("进入[" + methodName + "]方法,参数为:" + sb.toString());

            // 继续执行方法
            try {
                joinPoint.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            System.out.println(methodName + "方法执行结束");

        }
}
