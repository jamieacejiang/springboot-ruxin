package com.csy.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.csy.demo.pojo.MessageResult;
import com.csy.demo.pojo.Role;
import com.csy.demo.pojo.User;
import com.csy.demo.service.RoleService;
import com.csy.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @title: 系统管理
 * @description: 系统管理统一controller
 * @author: jiangman
 * @time: 2018/04/24 11:09
 */
@Controller
@RequestMapping("/system")
public class SystemController {

    @Autowired
    private UserService userService;//注入用户相关操作service

    @Autowired
    private RoleService roleService;//注入角色相关操作service


    /**
    * @Description: 跳转用户管理页面
    * @author: jiangman
    * @date: 2018/5/4 11:16
    */
    @RequestMapping("/toUserManage.do")
    public String toUserManage(){
        return "userManage";
    }
    
    /**
    * @Description: 查询用户列表信息给前台bootstrap-table
    * @param username 用户名
    * @param roleId 角色
    * @param limit 页面大小
    * @param offset 页面大小起始值
    * @author: jiangman
    * @date: 2018/5/4 11:17
    */
    @RequestMapping("/queryPage.do")
    @ResponseBody
    public JSONObject queryUsers(String username, String roleId, int limit, int offset){
        //查询结果列表
        List<User> resultList = new ArrayList<User>();
        resultList = userService.queryPagerList(username, roleId,limit, offset);
        JSONObject json = new JSONObject();
        int total = userService.getTotal(username, roleId);
        json.put("rows", resultList);
        json.put("total", total);
        return json;
    }

    /**
    * @Description: 查询单个用户信息
    * @author: jiangman
    * @date: 2018/5/4 11:16
    */
    @RequestMapping("/queryUserBean.do")
    @ResponseBody
    public User queryUserBean(String id){
        User u = new User();
        u.setId(id);
        User user = userService.queryBean(u);
        return user;
    }
    
    /**
    * @Description: 查询角色列表信息
    * @author: jiangman
    * @date: 2018/5/4 11:18
    */
    @RequestMapping("/queryRoleList.do")
    @ResponseBody
    public List<Role> queryRoleList(){
        List<Role> roleList = roleService.queryList();
        return roleList;
    }

    /**
    * @Description: 创建用户
    * @author: jiangman
    * @date: 2018/5/29 10:42
    */
    @RequestMapping(value = "/register.do",method = RequestMethod.POST)
    @ResponseBody
    public MessageResult register(@RequestBody User newUser){
        int i = userService.register(newUser);
        //如果后台抛异常是不会走下面的代码的，前台的error会自动接收
        MessageResult mr = new MessageResult();
        if(i>0){
            mr.setStatus(0);//0:成功
        }else{
            mr.setStatus(1);
            mr.setMsg("密码加密失败！");
        }
        return mr;
    }

    /**
    * @Description: 修改用户
    * @author: jiangman
    * @date: 2018/5/31 11:47
    */
    @RequestMapping(value = "/updateUser.do",method = RequestMethod.POST)
    @ResponseBody
    public MessageResult updateUser(@RequestBody User newUser){
        int i = userService.updateUser(newUser);
        MessageResult mr = new MessageResult();
        if(i>0){
            mr.setStatus(0);//0:成功
        }else{
            mr.setStatus(1);
            mr.setMsg("密码加密失败！");
        }
        return mr;
    }

    /**
    * @Description: 删除用户
    * @author: jiangman
    * @date: 2018/5/31 16:37
    */
    @RequestMapping("/deleteUser.do")
    @ResponseBody
    public MessageResult deleteUser(String id){
        User user = new User();
        user.setId(id);
        int i = userService.deleteUser(user);
        MessageResult mr = new MessageResult();
        if(i>0){
            mr.setStatus(0);//0:成功
        }else{
            mr.setStatus(1);
        }
        return mr;
    }
}
