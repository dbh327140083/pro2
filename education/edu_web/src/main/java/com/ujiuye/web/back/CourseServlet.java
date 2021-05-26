package com.ujiuye.web.back;

import com.ujiuye.entity.ResuletVo;
import com.ujiuye.utils.JsonUtils;
import com.ujiuye.utils.UploadUtils;
import com.ujiuye.web.BaseServlet;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/courses")
@MultipartConfig
public class CourseServlet extends BaseServlet {
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

}
