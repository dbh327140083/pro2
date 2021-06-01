package com.ujiuye.web.before;

import com.ujiuye.entity.ResuletVo;
import com.ujiuye.entity.User;
import com.ujiuye.service.UserService;
import com.ujiuye.service.impl.UserServiceImpl;
import com.ujiuye.utils.JsonUtils;
import com.ujiuye.web.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/users")
public class UserServlet extends BaseServlet {
    ResuletVo vo;
    UserService service=   new UserServiceImpl();

    public void testPhone(HttpServletRequest request, HttpServletResponse response) throws IOException {
         //接受参数
        String phone = request.getParameter("phone");
         //调用业务
         User user= service.testPhone(phone);
         //响应处理结果
        if(user==null){
            //可用
            response.getWriter().print(0);
        }else{
            //不可用 已注册
            response.getWriter().print(1);
        }
    }

    public void register(HttpServletRequest request, HttpServletResponse response){
        //接受参数
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String pwd = request.getParameter("pwd");
        User user = new User();
        user.setName(name);
        user.setPhone(phone);
        user.setPassword(pwd);
        //调用业务
        int row =service.register(user);
        //响应处理结果
        if (row > 0) {
            vo = new ResuletVo(200, "注册成功", null);
        } else {
            vo = new ResuletVo(500, "注册失败", null);
        }
        JsonUtils.objToJson(vo,response);
    }

    public void login(HttpServletRequest request, HttpServletResponse response){
        //接受参数
        String phone = request.getParameter("phone");
        String pwd = request.getParameter("pwd");
        //调用业务
        User user= service.login_Before(phone,pwd);
        //响应处理结果
        if (user == null) {
            vo = new ResuletVo(500, "用户名或密码错误", null);
        } else {
            vo = new ResuletVo(200, "登录成功", user);
        }

        JsonUtils.objToJson(vo,response);
    }


}
