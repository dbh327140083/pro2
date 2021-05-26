package com.ujiuye.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JsonUtils {

    /*
        将对象转成json直接响应页面
     */
    public static void objToJson(Object obj, HttpServletResponse response)  {
        response.setContentType("text/html;charset=utf-8");
        ObjectMapper mapper = new ObjectMapper();
        String s = null;
        try {
            s = mapper.writeValueAsString(obj);
            response.getWriter().print(s);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
