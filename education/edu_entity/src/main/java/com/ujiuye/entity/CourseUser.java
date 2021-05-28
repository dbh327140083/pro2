package com.ujiuye.entity;


public class CourseUser {
  //.user.name
  //.course.CourseName
  private int id; //编号
  private int cid;//课程cid 11
  private int uid;//用户cid  1

  private User user; //用户
  private Course course;// 11 课程

  public CourseUser() {
  }

  public CourseUser(int id, int cid, int uid, User user, Course course) {
    this.id = id;
    this.cid = cid;
    this.uid = uid;
    this.user = user;
    this.course = course;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Course getCourse() {
    return course;
  }

  public void setCourse(Course course) {
    this.course = course;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }


  public int getCid() {
    return cid;
  }

  public void setCid(int cid) {
    this.cid = cid;
  }


  public int getUid() {
    return uid;
  }

  public void setUid(int uid) {
    this.uid = uid;
  }

}
