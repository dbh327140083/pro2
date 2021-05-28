package com.ujiuye.service;

import com.ujiuye.entity.Course;
import com.ujiuye.entity.Coursedetail;
import com.ujiuye.utils.PageBean;

import java.util.List;

public interface CourseService {
    /*
        添加课程业务
     */
    int insertCourse(Course course);
    /*
        分页查询课程业务
     */
    PageBean<Course> findAllByPage(String currentPage, String pageSize, String search);
    /*
        修改课程业务
     */
    int updateCourse(Course course);
    /*
        批量删除业务
     */
    int delAll(String ids);
    /*
        添加课程明细
     */
    int insertCourseDetail(Coursedetail coursedetail);
    /*
        查询所有的课程信息
     */
    List<Course> findCourseNames();
}
