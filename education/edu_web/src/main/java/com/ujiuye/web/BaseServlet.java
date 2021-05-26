package com.ujiuye.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取要调用的方法 http://localhost:8080/edu/user?method=login
        String method = req.getParameter("method");
        //获取子类
        Class<? extends BaseServlet> claz = this.getClass();
        try {
             //通过方法名和参数类型去字节码文件获取到该方法
            Method m = claz.getMethod(method, HttpServletRequest.class, HttpServletResponse.class);
            //方法调用
            m.invoke(claz.newInstance(),req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
