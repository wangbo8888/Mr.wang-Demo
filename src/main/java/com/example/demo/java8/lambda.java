package com.example.demo.java8;

import com.example.demo.servlet.User;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @BelongsProject: demo
 * @BelongsPackage: com.example.demo.java8
 * @Author: Mr.wang
 * @Date: 2022/5/25 22:12
 * @Description:
 */
public class lambda {

    public static void main(String[] args) {
        List<User> list = new ArrayList<>();
        User user = new User("12","34");
        list.add(user);
        list.add(new User("xz","34"));
        list.add(new User("xl","22"));
        list.add(new User("lh",null));
        list.add(new User("lh","31"));
        List<User> lis2 = new ArrayList<>();
        list.add(new User("xz","34"));
        list.add(new User("xl","1"));
        list.add(new User("lh","24"));
        list.add(new User("lp","31"));
//        list.stream().filter(List::contains()).collect(Collectors.toMap(User::getUsername, Function.identity()));
//        Assert.isTrue(list.size() == 3,"list数据不对");
        Map<String, Map<String, List<User>>> collect = list.stream()
                .filter(l ->!StringUtils.isEmpty(l.getPassword()))
                .collect(Collectors.groupingBy(User::getUsername, Collectors.groupingBy(User::getPassword)));
        System.out.println("111111111");


    }

    public  static String groupKey (User user){
        return user.getUsername()+user.getPassword();
    }



}
