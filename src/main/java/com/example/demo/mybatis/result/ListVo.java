package com.example.demo.mybatis.result;

import com.example.demo.mybatis.dao.Orders;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @BelongsProject: Mr.wang-Demo
 * @BelongsPackage: com.example.demo.mybatis.result
 * @Author: Mr.wang
 * @Date: 2022/6/12 12:13
 * @Description:
 */
@Data
public class ListVo {
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

    private List<Orders> list;
}
