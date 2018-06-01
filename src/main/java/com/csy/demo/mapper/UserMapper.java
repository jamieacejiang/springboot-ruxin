package com.csy.demo.mapper;


import com.csy.demo.pojo.User;

import java.util.List;
import java.util.Map;

/**
 * @title: 用户mapper接口
 * @description:
 * @author: jiangman
 * @time: 2018/04/13 10:15
 */
public interface UserMapper {
    /**
    * @Description: 这个是真删除-不能用不能用，想用删除和下面的update用一个
    * @author: jiangman
    * @date: 2018/5/31 15:24
    */
    int deleteByPrimaryKey(Integer id);

    /**
    * @Description: 新增用户
    * @author: jiangman
    * @date: 2018/5/31 15:14
    */
    int insert(User record);

    /**
    * @Description: 新增用户-这个新增应该用不到吧
    * @author: jiangman
    * @date: 2018/5/31 15:20
    */
    int insertSelective(User record);

    /**
    * @Description: 通过ID(标识)查询单个实体-应该也用不到，我写成queryBean了
    * @author: jiangman
    * @date: 2018/5/31 15:23
    */
    User selectByPrimaryKey(Integer id);

    /**
    * @Description: 更新用户-只更新不为空的字段
    * @author: jiangman
    * @date: 2018/5/31 15:17
    */
    int updateByPrimaryKeySelective(User record);

    /**
    * @Description: 更新用户-全部更新成所传数据的样子，空的就更新为空
    * @author: jiangman
    * @date: 2018/5/31 15:16
    */
    int updateByPrimaryKey(User record);

    /**
    * @Description: 查询单个实体
    * @author: jiangman
    * @date: 2018/4/14 9:52
    */
    User queryBean(User user);

    /**
    * @Description: 分页查询
    * @author: jiangman
    * @date: 2018/4/30 19:40
    */
    List<User> queryPagerList(Map<String, Object> map);

    /**
    * @Description: 分页查询
    * @author: jiangman
    * @date: 2018/4/30 19:40
    */
    Integer getTotal(User user);
}