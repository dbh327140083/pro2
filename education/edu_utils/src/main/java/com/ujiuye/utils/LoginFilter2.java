package com.ujiuye.utils;

import com.ujiuye.entity.ResuletVo;
import com.ujiuye.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter2  implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request=(HttpServletRequest) servletRequest;
        HttpServletResponse response=(HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();
        //请求来源  Referer: http://127.0.0.1:5501/
        String referer = request.getHeader("Referer");

        //获取到用户
        User user =(User) session.getAttribute("user");
        if (user==null) {
            //没有登录
            String method = request.getParameter("method");
            //http://localhost/edu/code
            //获取不包含端口的请求路径
            String requestURI = request.getRequestURI();//   /edu/code
            if(referer.contains("5501") ||  "login".equals(method) || "findUser".equals(method) || "loginOut".equals(method) || requestURI.contains("code")){
                //放行
                filterChain.doFilter(request,response);
            }else{
                //登录
                ResuletVo vo = new ResuletVo(401, "请先登录再操作", null);
                JsonUtils.objToJson(vo, response);
            }

        }else{
            //放行
            filterChain.doFilter(request,response);
        }

    }









    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
