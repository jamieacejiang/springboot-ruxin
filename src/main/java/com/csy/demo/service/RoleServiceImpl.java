package com.csy.demo.service;

import com.csy.demo.mapper.RoleMapper;
import com.csy.demo.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @title:
 * @description:
 * @author: jiangman
 * @time: 2018/05/07 10:14
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> queryList() {
        return roleMapper.queryList();
    }
}
