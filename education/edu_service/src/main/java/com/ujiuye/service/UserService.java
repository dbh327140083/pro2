package com.ujiuye.service;

import com.ujiuye.entity.User;

public interface UserService {
    /*
        登录业务

     */
    public User Login(String username, String password);
}
