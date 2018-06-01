package com.csy.demo.service;

import com.csy.demo.pojo.Role;

import java.util.List;

/**
 * @title: 角色相关服务接口
 * @description:
 * @author: jiangman
 * @time: 2018/05/04 11:21
 */
public interface RoleService {

    /**
    * @Description: 查询所有角色列表
    * @author: jiangman
    * @date: 2018/5/7 10:13
    */
    List<Role> queryList();
}
