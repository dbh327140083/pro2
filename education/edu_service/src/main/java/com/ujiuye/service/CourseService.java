package com.ujiuye.service;

import com.ujiuye.entity.Course;
import com.ujiuye.entity.Coursedetail;
import com.ujiuye.utils.PageBean;

import java.util.List;

public abstract class CourseService {
    /*
        添加课程业务
     */
    public abstract int insertCourse(Course course);
    /*
        分页查询课程业务
     */
    public abstract PageBean<Course> findAllByPage(String currentPage, String pageSize, String search);
    /*
        修改课程业务
     */
    public abstract int updateCourse(Course course);
    /*
        批量删除业务
     */
    public abstract int delAll(String ids);
    /*
        添加课程明细
     */
    public abstract int insertCourseDetail(Coursedetail coursedetail);
    /*
        查询所有的课程信息
     */
    public abstract List<Course> findCourseNames();
    /*
        根据课程类型查询课程信息业务
     */
    public abstract List<Course> findCourseType(String courseType);
    /*
        前台课程检索业务
     */
    public abstract PageBean<Course> findAllByPage_Before(String courseName, String courseType, String currentPage, String pageSize);
    /*
        根据课程编号查询课程业务
     */
    public abstract Course findCourse(String cid);
}
