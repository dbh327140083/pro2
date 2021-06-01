package com.ujiuye.web.before;

import com.ujiuye.entity.Course;
import com.ujiuye.service.CourseService;
import com.ujiuye.service.impl.CourseServiceImpl;
import com.ujiuye.utils.JsonUtils;
import com.ujiuye.utils.PageBean;
import com.ujiuye.web.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/course")
public class CourseServlet extends BaseServlet {
    CourseService service =new CourseServiceImpl();
    public void findCourseType(HttpServletRequest request, HttpServletResponse response){
        //接受课程类型
        String courseType = request.getParameter("courseType");
        //调用业务
        List<Course> list =service.findCourseType(courseType);
        //响应处理结果
        JsonUtils.objToJson(list,response);
    }

    public void findByPage(HttpServletRequest request, HttpServletResponse response){
        //获取查询条件‘
        String courseName = request.getParameter("courseName");
        String courseType = request.getParameter("courseType");
        //获取分页相关的参数
        String currentPage = request.getParameter("currentPage");
        String pageSize = request.getParameter("pageSize");
        //调用业务
        PageBean<Course> pb = service.findAllByPage_Before(courseName,courseType,currentPage,pageSize);
        //响应数据
        JsonUtils.objToJson(pb,response);
    }
    public void findCourse(HttpServletRequest request, HttpServletResponse response) {
        //获取课程cid
        String cid = request.getParameter("cid");
        //调用业务
        Course course= service.findCourse(cid);
        //响应数据
        JsonUtils.objToJson(course,response);
    }

}
