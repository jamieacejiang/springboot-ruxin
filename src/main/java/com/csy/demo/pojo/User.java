package com.csy.demo.pojo;

/**
 * @title: 用户实体
 * @description:
 * @author: jiangman
 * @time: 2018/04/13 19:19
 */
public class User {
    //id(主键)
    private String id;
    //登录名
    private String username;
    //密码
    private String password;
    //状态(A,P)
    private String sts;
    //状态名称(在用,注销)
    private String stsName;
    //注册时间
    private String registeTime;
    //修改时间
    private String updateTime;
    //角色ID
    private String roleId;

    //关联角色对象
    private Role role;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSts() {
        return sts;
    }

    public void setSts(String sts) {
        this.sts = sts;
    }

    public String getStsName() {
        return stsName;
    }

    public void setStsName(String stsName) {
        this.stsName = stsName;
    }

    public String getRegisteTime() {
        return registeTime;
    }

    public void setRegisteTime(String registeTime) {
        this.registeTime = registeTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
