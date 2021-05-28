package com.ujiuye.web.back;

import com.ujiuye.entity.ResuletVo;
import com.ujiuye.entity.User;
import com.ujiuye.service.UserService;
import com.ujiuye.service.impl.UserServiceImpl;
import com.ujiuye.utils.JsonUtils;
import com.ujiuye.utils.PageBean;
import com.ujiuye.web.BaseServlet;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.util.Map;

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

    public void findByPage(HttpServletRequest request, HttpServletResponse response){
        //获取请求参数
        String currentPage = request.getParameter("currentPage");
        String pageSize = request.getParameter("pageSize");
        String search = request.getParameter("search");
        //调用业务
        PageBean<User> list =service.findByPage(currentPage,pageSize,search);
        //响应处理结果
        JsonUtils.objToJson(list,response);
    }

    public void insertUser(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException {
        //解决请求参数到map
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        //将map中的数据封装到user中
        BeanUtils.populate(user,map);
        //调用业务
        int row = service.insertUser(user);
        //响应处理结果
        if (row > 0) {
            vo = new ResuletVo(200, "添加用户成功", null);
        } else {
            vo = new ResuletVo(500, "添加用户失败", null);
        }
        JsonUtils.objToJson(vo,response);
    }

    public void delAll(HttpServletRequest request, HttpServletResponse response){
        //接受参数
        String ids = request.getParameter("ids");
        //调用业务
        int row =service.delAll(ids);
        //响应处理结果
        if (row > 0) {
            vo = new ResuletVo(200, "批量删除成功", null);
        } else {
            vo = new ResuletVo(500, "批量删除失败", null);
        }
        JsonUtils.objToJson(vo,response);
    }

    public void updateUser(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException {
        //解决请求参数到map
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        //将map中的数据封装到user中
        BeanUtils.populate(user,map);
        //调用业务
        int row =service.updateUser(user);
        //响应处理结果
        if (row > 0) {
            vo = new ResuletVo(200, "修改用户成功", null);
        } else {
            vo = new ResuletVo(500, "修改用户失败", null);
        }
        JsonUtils.objToJson(vo,response);
    }

    public void loginOut(HttpServletRequest request, HttpServletResponse response){
        //销毁session
        request.getSession().invalidate();
        vo = new ResuletVo(200, "退出登录成功", null);
        JsonUtils.objToJson(vo,response);
    }

}
