package com.example.demo.Convert;

/**
 * @BelongsProject: Mr.wang-Demo
 * @BelongsPackage: com.example.demo.Convert
 * @Author: Mr.wang
 * @Date: 2022/6/12 15:57
 * @Description:
 */
public interface Generics<T> {

    T get();
    /**
     * 泛型方法
     * */
    T test(T t);


}
