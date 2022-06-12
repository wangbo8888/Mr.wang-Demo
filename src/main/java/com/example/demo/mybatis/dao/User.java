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
public class User implements Serializable,Cloneable{
    /**  */
    private Integer id ;
    /** 用户名称 */
    private String username ;
    /** 生日 */
    private Date birthday ;
    /** 性别 */
    private String sex ;
    /** 地址 */
    private String address ;

    /**  */
    public Integer getId(){
        return this.id;
    }
    /**  */
    public void setId(Integer id){
        this.id=id;
    }
    /** 用户名称 */
    public String getUsername(){
        return this.username;
    }
    /** 用户名称 */
    public void setUsername(String username){
        this.username=username;
    }
    /** 生日 */
    public Date getBirthday(){
        return this.birthday;
    }
    /** 生日 */
    public void setBirthday(Date birthday){
        this.birthday=birthday;
    }
    /** 性别 */
    public String getSex(){
        return this.sex;
    }
    /** 性别 */
    public void setSex(String sex){
        this.sex=sex;
    }
    /** 地址 */
    public String getAddress(){
        return this.address;
    }
    /** 地址 */
    public void setAddress(String address){
        this.address=address;
    }
}