package com.ujiuye.utils;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class UploadUtils {
    /*
        上传文件到指定位置
          返回值:上传文件的文件名
     */

    public static String upload(HttpServletRequest request){

        try {
            //根据文件名获取文件资源
            Part part = request.getPart("file");
            //从文件资源中获取文件名称  javaWeb.jpg
            String fileName = part.getSubmittedFileName();
            //重命名文件名  sda12-dsad1-adasjavaWeb.jpg
            fileName= UUID.randomUUID()+fileName;
            File file = new File("D:/xm_upload");
            if(!file.exists()){
                //如果文件夹不存在
                file.mkdir();//创建文件夹
            }
            //拼接写入路径
            String path =file+"/"+fileName;
            //将文件资源写入到指定路径
            part.write(path);
            return fileName;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return  null;
    }

}
