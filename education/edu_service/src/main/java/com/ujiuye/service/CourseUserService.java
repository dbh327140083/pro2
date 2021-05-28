package com.ujiuye.service;

import com.ujiuye.entity.CourseUser;
import com.ujiuye.utils.PageBean;

public interface CourseUserService  {
    //分页查询业务
    PageBean<CourseUser> findAllByPage(String currentPage, String pageSize);
    //修改选课业务
    int updateCourseUser(String cid, String id);
}
