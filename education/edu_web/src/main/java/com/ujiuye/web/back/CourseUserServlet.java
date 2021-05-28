package com.ujiuye.web.back;

import com.ujiuye.entity.CourseUser;
import com.ujiuye.entity.ResuletVo;
import com.ujiuye.service.CourseUserService;
import com.ujiuye.service.impl.CourseUserServiceImpl;
import com.ujiuye.utils.JsonUtils;
import com.ujiuye.utils.PageBean;
import com.ujiuye.web.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/c_u")
public class CourseUserServlet extends BaseServlet {
    ResuletVo vo;
    CourseUserService service = new CourseUserServiceImpl();
    public void findAllByPage(HttpServletRequest request, HttpServletResponse response){

        //接受请求参数
        String currentPage = request.getParameter("currentPage");
        String pageSize = request.getParameter("pageSize");
        //调用业务
        PageBean<CourseUser> pb =service.findAllByPage(currentPage,pageSize);
        //响应数据
        JsonUtils.objToJson(pb,response);
    }

    public void updateCourseUser(HttpServletRequest request, HttpServletResponse response){

        //接受请求参数
        String id = request.getParameter("id");
        String cid = request.getParameter("cid");
        //调用业务
        int row = service.updateCourseUser(cid, id);

        //响应处理结果
        if(row>0){
             vo = new ResuletVo(200, "修改选课成功", null);
        }else{
             vo = new ResuletVo(500, "修改选课失败", null);
        }

        JsonUtils.objToJson(vo,response);

    }
}
