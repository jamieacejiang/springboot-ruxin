package com.csy.demo.service;

import com.csy.demo.pojo.MessageResult;
import com.csy.demo.pojo.User;

import java.util.List;

/**
 * @title: 用户相关服务接口
 * @description:
 * @author: jiangman
 * @time: 2018/04/24 13:29
 */
public interface UserService {
    
    /**
    * @Description: 登录校验
    * @author: jiangman
    * @date: 2018/4/13 19:25
    */
    public MessageResult checkLogin(User user);

    /**
    * @Description: 分页查询返回数据
    * @author: jiangman
    * @date: 2018/4/30 19:29
    */
    List<User> queryPagerList(String username, String password, int limit, int offset);

    /**
    * @Description: 分页查询返回总数
    * @author: jiangman
    * @date: 2018/4/30 19:30
    */
    int getTotal(String username, String password);
    
    /**
    * @Description: 查询单个实体
    * @author: jiangman
    * @date: 2018/4/30 21:45
    */
    User queryBean(User u);

    /**
    * @Description: 新增用户
    * @author: jiangman
    * @date: 2018/5/29 10:51
    */
    int register(User user);

    /**
    * @Description: 更新用户信息
    * @author: jiangman
    * @date: 2018/5/31 15:33
    */
    int updateUser(User newUser);

    /**
    * @Description: 删除用户
    * @author: jiangman
    * @date: 2018/5/31 16:24
    */
    int deleteUser(User user);
}
