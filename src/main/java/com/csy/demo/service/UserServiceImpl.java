package com.csy.demo.service;

import com.csy.demo.mapper.UserMapper;
import com.csy.demo.pojo.MessageResult;
import com.csy.demo.pojo.User;
import com.csy.demo.util.Base64Util;
import com.csy.demo.util.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @title: 用户service实现
 * @description:
 * @author: jiangman
 * @time: 2018/04/24 13:30
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public MessageResult checkLogin(User user) {

        MessageResult result = new MessageResult();

        //将用户输入的password再加密，与数据库比对
        String md5_password = "";
        if(StringUtils.isNotBlank(user.getPassword())){
            md5_password = Base64Util.encode(user.getPassword());
            if(StringUtils.isBlank(md5_password)){
                result.setStatus(2);
                result.setData("密码加密失败！");
            }else{
                user.setPassword(md5_password);
                User user1 = userMapper.queryBean(user);
                if(user1 == null){
                    result.setStatus(1);
                    result.setMsg("用户名或密码错误！");
                }else{
                    result.setStatus(0);
                    result.setMsg("登陆成功！");
                    result.setData(user1);//返回用户
                }
            }
        }else{
            result.setStatus(2);
            result.setMsg("密码不能为空！");
        }
        return result;
    }

    @Override
    public List<User> queryPagerList(String username, String roleId, int limit, int offset) {
        //parametertype传多个参数,所以用map封装一下
        Map<String,Object> map =new HashMap<String,Object>();
        map.put("username", username);
        map.put("roleId", roleId);
        //由于在mysql中*有问题所以提前计算下
        if((offset!=-1)&&(limit!=-1)){
            offset = offset-limit+limit;
        }
        map.put("offset", offset);
        map.put("limit", limit);
        List<User> resultList = userMapper.queryPagerList(map);
        for(User user : resultList) {
            String pwd = user.getPassword();
            String password_md5 = "";
            if (StringUtils.isNotBlank(pwd)) {
                password_md5 = Base64Util.decode(pwd);//这里没有校验为空，如果数据库中的数据转换失败，就展示为空吧，新增的时候不行。
            }
            user.setPassword(password_md5);
        }
        return resultList;
    }

    @Override
    public int getTotal(String username, String roleId) {
        User user = new User();
        user.setUsername(username);
        user.setRoleId(roleId);
        int total = userMapper.getTotal(user);
        return total;
    }

    @Override
    public User queryBean(User user) {
        User u = userMapper.queryBean(user);
        String password = Base64Util.decode(u.getPassword());
        u.setPassword(password);
        return u;
    }

    @Override
    public int register(User user) {
        int i = 0;
        if(StringUtils.isNotBlank(user.getPassword())) {
            String md5_password = Base64Util.encode(user.getPassword());
            if (StringUtils.isNotBlank(md5_password)) {
                user.setPassword(md5_password);
                user.setSts("A");
                user.setRegisteTime(DateUtil.getNowDateStr());
                i = userMapper.insert(user);
            }
        }
        return i;
    }

    @Override
    public int updateUser(User user) {
        int i = 0;
        if(StringUtils.isNotBlank(user.getPassword())) {
            String md5_password = Base64Util.encode(user.getPassword());
            if (StringUtils.isNotBlank(md5_password)) {
                user.setPassword(md5_password);
                user.setUpdateTime(DateUtil.getNowDateStr());
                i = userMapper.updateByPrimaryKeySelective(user);
            }
        }
        return i;
    }

    @Override
    public int deleteUser(User user) {
        int i = 0;
        user.setSts("P");
        user.setUpdateTime(DateUtil.getNowDateStr());
        i = userMapper.updateByPrimaryKeySelective(user);
        return i;
    }
}
