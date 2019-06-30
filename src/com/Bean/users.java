package com.Bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.sql.Timestamp;

public class users {
    public static final String table="users";
    @JSONField(name ="username")
    private String username;
    @JSONField(name ="password")
    private String password;
    @JSONField(name ="nickname")
    private String nickname;
    @JSONField(name ="email")
    private String email;
    @JSONField(name ="sex")
    private boolean sex;
    @JSONField(name ="privilege")
    private int privilege;
    @JSONField(name ="state")
    private int state;
    @JSONField(name ="active_code")
    private String active_code;
    @JSONField(name ="updatetime")
    private Timestamp updatetime;

    public static String getTable() {
        return table;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public int getPrivilege() {
        return privilege;
    }

    public void setPrivilege(int privilege) {
        this.privilege = privilege;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getActive_code() {
        return active_code;
    }

    public void setActive_code(String active_code) {
        this.active_code = active_code;
    }

    public Timestamp getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Timestamp updatetime) {
        this.updatetime = updatetime;
    }
}
