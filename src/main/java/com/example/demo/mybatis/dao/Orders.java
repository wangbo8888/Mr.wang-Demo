package com.example.demo.mybatis.dao;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

 /**
 * ;
 * @author : http://www.chiner.pro
 * @date : 2022-6-12
 */
 @Data
public class Orders implements Serializable,Cloneable{
    /**  */
    private Integer id ;
    /**  */
    private Integer uid ;
///**/    /**  */
    private Date ordertime ;
//    /**  */
    private double money ;
    /**  */
    private String ordetype ;


}