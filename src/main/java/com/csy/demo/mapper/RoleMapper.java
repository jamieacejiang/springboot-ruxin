package com.csy.demo.mapper;

import com.csy.demo.pojo.Role;

import java.util.List;

/**
 * @title: 角色mapper接口
 * @description:
 * @author: jiangman
 * @time: 2018/05/07 10:15
 */
public interface RoleMapper {
    
    /**
    * @Description: 查询角色列表
    * @author: jiangman
    * @date: 2018/5/7 10:16
    */
    List<Role> queryList();
}
