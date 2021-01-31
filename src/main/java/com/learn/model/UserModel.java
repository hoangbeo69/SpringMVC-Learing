package com.learn.model;

import java.sql.Timestamp;

public class UserModel extends AbstractModel<UserModel>{
    private long id;
    private String userName;
    private String fullName;
    private String passWord;
    private long status;
    private long roleId;
    private RoleModel roleModel = new RoleModel();

    public RoleModel getRoleModel() {
        return roleModel;
    }

    public void setRoleModel(RoleModel roleModel) {
        this.roleModel = roleModel;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public long isStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }
}
