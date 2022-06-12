package com.example.demo.java8;

import com.example.demo.servlet.UserTest;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
        List<UserTest> list = new ArrayList<>();
        UserTest userTest = new UserTest("12","34");
        list.add(userTest);
        list.add(new UserTest("xz","34"));
        list.add(new UserTest("xl","22"));
        list.add(new UserTest("lh",null));
        list.add(new UserTest("lh","31"));
        List<UserTest> lis2 = new ArrayList<>();
        list.add(new UserTest("xz","34"));
        list.add(new UserTest("xl","1"));
        list.add(new UserTest("lh","24"));
        list.add(new UserTest("lp","31"));
//        list.stream().filter(List::contains()).collect(Collectors.toMap(User::getUsername, Function.identity()));
//        Assert.isTrue(list.size() == 3,"list数据不对");
        Map<String, Map<String, List<UserTest>>> collect = list.stream()
                .filter(l ->!StringUtils.isEmpty(l.getPassword()))
                .collect(Collectors.groupingBy(UserTest::getUsername, Collectors.groupingBy(UserTest::getPassword)));
        System.out.println("111111111");


    }

    public  static String groupKey (UserTest userTest){
        return userTest.getUsername()+ userTest.getPassword();
    }



}
