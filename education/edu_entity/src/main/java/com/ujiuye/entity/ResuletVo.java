package com.ujiuye.entity;
/*
    通用的返回数据格式
 */
public class ResuletVo {

    private int code;//状态码

    private String message;//描述

    private Object data;//返回的数据


    public ResuletVo() {
    }

    public ResuletVo(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
