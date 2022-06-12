package com.example.demo.mybatis.mapper;

import java.util.List;

import com.example.demo.mybatis.dao.User;
import com.example.demo.mybatis.result.ListVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

 /**
 * ;(user)表数据库访问层
 * @author : http://www.chiner.pro
 * @date : 2022-6-12
 */
public interface UserMapper{
    /** 
     * 通过ID查询单条数据 
     *
     * @param id 主键
     * @return 实例对象
     */
    User queryById(Integer id);
    /** 
     * 分页查询指定行数据
     *
     * @param user 查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<User> queryAllByLimit(User user, @Param("pageable") Pageable pageable);
    /** 
     * 统计总行数
     *
     * @param user 查询条件
     * @return 总行数
     */
    long count(User user);
    /** 
     * 新增数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int insert(User user);
    /** 
     * 批量新增数据
     *
     * @param entities List<User> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<User> entities);
    /** 
     * 批量新增或按主键更新数据
     *
     * @param entities List<User> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<User> entities);
    /** 
     * 更新数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int update(User user);
    /** 
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

   List<ListVo> selectList();
}