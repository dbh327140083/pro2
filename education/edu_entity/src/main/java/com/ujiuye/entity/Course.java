package com.ujiuye.entity;


public class Course {

  private int cid; //课程编号
  private String courseName;//课程名称
  private String descs;//课程描述
  private int courseType;//课程类型 1 java 2数据库 3前端
  private String courseImage;//图片名称
  private String courseVideo;//视频名称
  private double coursePrice;//课程价格
  private int status;//课程状态  0未上架 1 上架 2下架
  private String createTime; //课程创建时间

  public Course() {
  }

  public Course(int cid, String courseName, String descs, int courseType, String courseImage, String courseVideo, double coursePrice, int status, String createTime) {
    this.cid = cid;
    this.courseName = courseName;
    this.descs = descs;
    this.courseType = courseType;
    this.courseImage = courseImage;
    this.courseVideo = courseVideo;
    this.coursePrice = coursePrice;
    this.status = status;
    this.createTime = createTime;
  }

  public int getCid() {
    return cid;
  }

  public void setCid(int cid) {
    this.cid = cid;
  }


  public String getCourseName() {
    return courseName;
  }

  public void setCourseName(String courseName) {
    this.courseName = courseName;
  }


  public String getDescs() {
    return descs;
  }

  public void setDescs(String descs) {
    this.descs = descs;
  }


  public int getCourseType() {
    return courseType;
  }

  public void setCourseType(int courseType) {
    this.courseType = courseType;
  }


  public String getCourseImage() {
    return courseImage;
  }

  public void setCourseImage(String courseImage) {
    this.courseImage = courseImage;
  }


  public String getCourseVideo() {
    return courseVideo;
  }

  public void setCourseVideo(String courseVideo) {
    this.courseVideo = courseVideo;
  }


  public double getCoursePrice() {
    return coursePrice;
  }

  public void setCoursePrice(double coursePrice) {
    this.coursePrice = coursePrice;
  }


  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }


  public String getCreateTime() {
    return createTime;
  }

  public void setCreateTime(String createTime) {
    this.createTime = createTime;
  }

}
