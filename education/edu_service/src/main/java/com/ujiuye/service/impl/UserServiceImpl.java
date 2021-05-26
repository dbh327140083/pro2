package com.ujiuye.service.impl;

import com.ujiuye.dao.UserDao;
import com.ujiuye.entity.User;
import com.ujiuye.service.UserService;

public class UserServiceImpl implements UserService {

    UserDao dao = new UserDao();

    @Override
    public User Login(String username, String password) {

        String sql = "select * from user where username =? and password =?";

        return dao.getEntity(sql,User.class,username,password);
    }
}
