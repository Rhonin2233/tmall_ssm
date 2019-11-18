package com.rhonin.myssm.service;

import com.rhonin.myssm.pojo.User;

import java.util.List;

public interface UserSevice {
    List<User> list();

    User findById(int id);

    void add(User user);

    void update(User user);

    void delete(int id);

    Boolean checkByName(String name);

    User login(User user);
}
