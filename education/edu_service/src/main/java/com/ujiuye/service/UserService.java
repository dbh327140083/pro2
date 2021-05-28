package com.ujiuye.service;

import com.ujiuye.entity.User;
import com.ujiuye.utils.PageBean;

public interface UserService {
    /*
        登录业务

     */
    public User Login(String username, String password);
    /*
        分页查询业务
     */
    PageBean<User> findByPage(String currentPage, String pageSize, String search);
    /*
        添加用户业务
     */
    int insertUser(User user);
    /*
        批量删除业务
     */
    int delAll(String ids);
    /*
        更新用户业务
     */
    int updateUser(User user);
}
