package com.ujiuye.web.back;

import com.ujiuye.entity.ResuletVo;
import com.ujiuye.entity.User;
import com.ujiuye.service.UserService;
import com.ujiuye.service.impl.UserServiceImpl;
import com.ujiuye.utils.JsonUtils;
import com.ujiuye.web.BaseServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.ResultSet;

@WebServlet("/user")
public class UserServlet extends BaseServlet {
    UserService service= new UserServiceImpl();
    ResuletVo vo;
    public void login(HttpServletRequest request, HttpServletResponse response){
        //获取数据
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String inputCode = request.getParameter("code");
        HttpSession session = request.getSession();
        //获取生成的验证码
        String verCode = (String) session.getAttribute("verCode");
        //判断验证码是否正确
        if (verCode.equalsIgnoreCase(inputCode)) {
            //调用业务查询用户名和密码
            User user = service.Login(username, password);
            if (user == null) {
                //查询失败
                vo = new ResuletVo(500, "用户名或密码错误", null);
            } else {
                //查询到了
                //获取用户状态
                int status = user.getStatus();
                //获取用户角色
                int role = user.getRole();
                if (status!=1) {
                    //账号封停
                    vo= new ResuletVo(500,"账号被封停",null);
                }else{
                    //账号没封
                    if (role!=1) {
                        //账号权限不足
                        vo= new ResuletVo(500,"权限不足",null);
                    }else{
                        //登录成功
                        vo= new ResuletVo(200,"登录成功",null);
                        //将用户信息存入session
                        session.setAttribute("user",user);
                    }

                }

            }

        } else {

            vo= new ResuletVo(500,"验证码错误",null);

        }
        //转成json直接响应
        JsonUtils.objToJson(vo,response);
    }

    public void findUser(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        //从sesssion中获取到用户信息
        User user = (User) session.getAttribute("user");
        if (user == null) {
            vo = new ResuletVo(500, "获取用户信息失败", null);
        } else {
            vo = new ResuletVo(200, "获取用户信息成功", user);
        }
        JsonUtils.objToJson(vo,response);
    }
}
