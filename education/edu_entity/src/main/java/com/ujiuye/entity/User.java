package com.ujiuye.entity;


public class User {

  private int uid;//用户id
  private String name;//姓名
  private String phone;//手机号码
  private int age;//年龄
  private int sex;//性别 0男 1女
  private String username;//用户名
  private String password;//密码
  private int status;//状态 1启用状态 2停用
  private String createtime;//创建时间
  private int role;//1 管理员 2总经理 3用户
  private String picture;//头像

  public User() {
  }

  public User(int uid, String name, String phone, int age, int sex, String username, String password, int status, String createtime, int role, String picture) {
    this.uid = uid;
    this.name = name;
    this.phone = phone;
    this.age = age;
    this.sex = sex;
    this.username = username;
    this.password = password;
    this.status = status;
    this.createtime = createtime;
    this.role = role;
    this.picture = picture;
  }

  public int getUid() {
    return uid;
  }

  public void setUid(int uid) {
    this.uid = uid;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }


  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }


  public int getSex() {
    return sex;
  }

  public void setSex(int sex) {
    this.sex = sex;
  }


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }


  public String getCreatetime() {
    return createtime;
  }

  public void setCreatetime(String createtime) {
    this.createtime = createtime;
  }


  public int getRole() {
    return role;
  }

  public void setRole(int role) {
    this.role = role;
  }


  public String getPicture() {
    return picture;
  }

  public void setPicture(String picture) {
    this.picture = picture;
  }

}
