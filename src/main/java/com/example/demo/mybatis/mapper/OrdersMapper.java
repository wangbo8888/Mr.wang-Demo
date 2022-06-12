package com.example.demo.mybatis.mapper;

import com.example.demo.mybatis.dao.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;


/**
 * ;(orders)表数据库访问层
 * @author : http://www.chiner.pro
 * @date : 2022-6-12
 */
@Mapper
public interface OrdersMapper{
    /** 
     * 通过ID查询单条数据 
     *
     * @param id 主键
     * @return 实例对象
     */
    Orders queryById(Integer id);
    /** 
     * 分页查询指定行数据
     *
     * @param orders 查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<Orders> queryAllByLimit(Orders orders, @Param("pageable") Pageable pageable);
    /** 
     * 统计总行数
     *
     * @param orders 查询条件
     * @return 总行数
     */
    long count(Orders orders);
    /** 
     * 新增数据
     *
     * @param orders 实例对象
     * @return 影响行数
     */
    int insert(Orders orders);
    /** 
     * 批量新增数据
     *
     * @param entities List<Orders> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Orders> entities);
    /** 
     * 批量新增或按主键更新数据
     *
     * @param entities List<Orders> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<Orders> entities);
    /** 
     * 更新数据
     *
     * @param orders 实例对象
     * @return 影响行数
     */
    int update(Orders orders);
    /** 
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);
}