package com.rhonin.myssm.pojo;

public class User {
    private Integer id;

    private String name;

    private String password;

    private String Anonymous;

    public String getAnonymous() {
        return Anonymous;
    }

    public void setAnonymous(String anonymous) {
        Anonymous = anonymous;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
}