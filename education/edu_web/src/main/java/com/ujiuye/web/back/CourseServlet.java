package com.ujiuye.web.back;

import com.ujiuye.entity.Course;
import com.ujiuye.entity.Coursedetail;
import com.ujiuye.entity.ResuletVo;
import com.ujiuye.service.CourseService;
import com.ujiuye.service.impl.CourseServiceImpl;
import com.ujiuye.utils.FileNameUtils;
import com.ujiuye.utils.JsonUtils;
import com.ujiuye.utils.PageBean;
import com.ujiuye.utils.UploadUtils;
import com.ujiuye.web.BaseServlet;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/courses")
@MultipartConfig
public class CourseServlet extends BaseServlet {
    CourseService service=  new CourseServiceImpl();
    ResuletVo vo;
    public void uploadFile(HttpServletRequest request, HttpServletResponse response){
        //调用上传组件的工具类  javaWeb.jpg   111.mp4
        String fileName = UploadUtils.upload(request);
        //根据文件后缀判断上次的是图片还是视频
        if (fileName.endsWith(".jpg") || fileName.endsWith(".png") || fileName.endsWith(".jpeg") || fileName.endsWith(".gif")) {
            //刚刚上传是个图片
            vo = new ResuletVo(200, "上传图片成功", fileName);
        } else {
            //刚刚上传是个视频
            vo = new ResuletVo(200, "上传视频成功", fileName);
        }
        JsonUtils.objToJson(vo,response);
    }


    public void removeFile(HttpServletRequest request,HttpServletResponse response){
        //获取页面提交的文件名   http://localhost/img/350af2b8-d4b3-458a-97d7-9fa5c26b0001java基础.jpg
        String fileName = request.getParameter("fileName");
        //截取文件名 350af2b8-d4b3-458a-97d7-9fa5c26b0001java基础.jpg
        fileName= FileNameUtils.subFileName(fileName);
        //构建磁盘路径
        File file = new File("D:/x_upload/" + fileName);
        //删除文件
        file.delete();

        //根据文件后缀判断上次的是图片还是视频
        if (fileName.endsWith(".jpg") || fileName.endsWith(".png") || fileName.endsWith(".jpeg") || fileName.endsWith(".gif")) {
            //刚刚上传是个图片
            vo = new ResuletVo(200, "删除图片成功", null);
        } else {
            //刚刚上传是个视频
            vo = new ResuletVo(200, "删除视频成功", null);
        }
        JsonUtils.objToJson(vo,response);
    }

    public void insertCourse(HttpServletRequest request,HttpServletResponse response) throws InvocationTargetException, IllegalAccessException {
        //将所有的请求数据放入Map中
        Map<String, String[]> map = request.getParameterMap();
        Course course = new Course();
        //将map中的数据放入course中
        BeanUtils.populate(course,map);
        course.setCourseImage(FileNameUtils.subFileName(request.getParameter("dialogImageUrl")));
        course.setCourseVideo(FileNameUtils.subFileName(request.getParameter("dialogVedioUrl")));
        //调用业务
        int row = service.insertCourse(course);
        //响应处理结果
        if (row > 0) {
            vo = new ResuletVo(200, "课程添加成功", null);
        } else {
            vo = new ResuletVo(500, "课程添加失败", null);
        }
        JsonUtils.objToJson(vo,response);
    }

    public void findAll(HttpServletRequest request,HttpServletResponse response){
        //接受请求参数
        String currentPage = request.getParameter("currentPage");
        String pageSize = request.getParameter("pageSize");
        String search = request.getParameter("search");
        //调用业务
        PageBean<Course> pb =service.findAllByPage(currentPage,pageSize,search);
        //响应数据
        JsonUtils.objToJson(pb,response);
    }

    public void updateCourse(HttpServletRequest request,HttpServletResponse response) throws InvocationTargetException, IllegalAccessException {
        //接受参数到map
        Map<String, String[]> map = request.getParameterMap();
        //创建对象
        Course course = new Course();
        //将map中的数据封装到course中
        BeanUtils.populate(course,map);
        course.setCourseImage(FileNameUtils.subFileName(request.getParameter("dialogImageUrl")));
        course.setCourseVideo(FileNameUtils.subFileName(request.getParameter("dialogVedioUrl")));
        //调用业务
        int row =service.updateCourse(course);
        //响应处理结果
        if (row > 0) {
            vo = new ResuletVo(200, "修改课程成功", null);
        } else {
            vo = new ResuletVo(500, "修改课程失败", null);
        }

        JsonUtils.objToJson(vo,response);
    }

    public void delAll(HttpServletRequest request,HttpServletResponse response){

        //获取ids
        String ids = request.getParameter("ids");
        //调用业务
        int row =  service.delAll(ids);
        //响应处理结果
        if (row > 0) {
            vo = new ResuletVo(200, "批量删除成功", null);
        } else {
            vo = new ResuletVo(500, "批量删除失败", null);
        }
        JsonUtils.objToJson(vo,response);
    }

    public void insertCourseDetail(HttpServletRequest request,HttpServletResponse response) throws InvocationTargetException, IllegalAccessException {
        //接受参数到map
        Map<String, String[]> map = request.getParameterMap();
        Coursedetail coursedetail = new Coursedetail();
        BeanUtils.populate(coursedetail,map);
        coursedetail.setUrl(FileNameUtils.subFileName(request.getParameter("url")));
        //调用业务
        int row = service.insertCourseDetail(coursedetail);
        if (row > 0) {
            vo = new ResuletVo(200, "添加明细成功", null);
        } else {
            vo = new ResuletVo(500, "添加明细失败", null);
        }
        JsonUtils.objToJson(vo,response);
    }

}
