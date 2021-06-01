package com.ujiuye.service.impl;

import com.ujiuye.dao.CourseDao;
import com.ujiuye.dao.CourseDetailDao;
import com.ujiuye.entity.Course;
import com.ujiuye.entity.Coursedetail;
import com.ujiuye.service.CourseService;
import com.ujiuye.utils.PageBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CourseServiceImpl extends CourseService {

    CourseDao dao= new CourseDao();
    CourseDetailDao courseDetailDao= new CourseDetailDao();
    @Override
    public int insertCourse(Course c) {
        String sql = "insert into course values(null,?,?,?,?,?,?,?,?)";
        Object arr[] ={c.getCourseName(),c.getDescs(),c.getCourseType(),c.getCourseImage(),c.getCourseVideo(),c.getCoursePrice(),c.getStatus(),new Date()};
        return dao.update(sql,arr);
    }

    @Override
    public PageBean<Course> findAllByPage(String currentPage, String pageSize, String search) {

        PageBean<Course> pb = new PageBean<>();
        int cpage = Integer.parseInt(currentPage);
        int size = Integer.parseInt(pageSize);
        pb.setCurrentPage(cpage);
        pb.setPageSize(size);
        //初始化sql语句
        StringBuilder sb = new StringBuilder("select * from course   ");
        //存放?占位符值
        ArrayList<Object> paramsList = new ArrayList<>();

        if (search!=null && !search.trim().equals("")) {
            //希望根据课程名称检索了
            sb.append("   where courseName like ?  ");
            paramsList.add("%"+search+"%");
        }
        //符合条件的数据
        List<Course> listEntity = dao.getListEntity(sb.toString(), Course.class, paramsList.toArray());
        int totalCount =listEntity.size();
        //总条数
        pb.setTotalCount(totalCount);
        //总页数
        int totalPage =totalCount%size==0?totalCount/size:totalCount/size+1;
        pb.setTotalPage(totalPage);
        //用户想看的数据
        sb.append(" limit ?,?  ");
        paramsList.add((cpage-1)*size);
        paramsList.add(size);
        List<Course> courseList = dao.getListEntity(sb.toString(), Course.class, paramsList.toArray());
        pb.setList(courseList);
        return pb;
    }

    @Override
    public int updateCourse(Course c) {
        String sql = "update course set courseName=?,descs=?,courseType=?,courseImage=?,courseVideo=?,coursePrice=?,status=?,createTime=? where cid =?";
        Object arr[] ={c.getCourseName(),c.getDescs(),c.getCourseType(),c.getCourseImage(),c.getCourseVideo(),c.getCoursePrice(),c.getStatus(),new Date(),c.getCid()};
        return dao.update(sql,arr);
    }

    @Override
    public int delAll(String ids) {
        //ids 1,2,3
        String sql ="delete from course where cid in ("+ids+")";

        return dao.update(sql);
    }

    @Override
    public int insertCourseDetail(Coursedetail c) {

        String sql = "insert into coursedetail values(null,?,?,?,?,?)";

        return dao.update(sql,c.getName(),c.getType(),c.getUrl(),c.getStart_data(),c.getCid());
    }

    @Override
    public List<Course> findCourseNames() {

        String sql = "select * from course";

        return dao.getListEntity(sql,Course.class);
    }

    @Override
    public List<Course> findCourseType(String courseType) {
        int size =0;
        if ("1".equals(courseType)) {
            //java课程
            size=8;
        }else if("2".equals(courseType)){
            //数据库课程
            size = 9;
        }else if("3".equals(courseType)){
            //前端课程
            size = 6;
        }

        String sql = "select *from course where courseType =? limit 0,?";

        return dao.getListEntity(sql,Course.class,courseType,size);
    }

    @Override
    public PageBean<Course> findAllByPage_Before(String courseName, String courseType, String currentPage, String pageSize) {

        int cpage = Integer.parseInt(currentPage);
        int size = Integer.parseInt(pageSize);
        PageBean<Course> pb = new PageBean<>();
        pb.setCurrentPage(cpage);
        pb.setPageSize(size);

        //定义初始化sql
        StringBuilder sb = new StringBuilder("select *from course   where 1=1   ");
        //创建集合用于存储?占位符的参数
        ArrayList<Object> params = new ArrayList<>();
        if (courseName!=null && !courseName.trim().equals("")) {
            //按照课程名称搜索
            sb.append("   and courseName like ?   ");
            params.add("%" + courseName + "%");
        }

        if (courseType!=null &&  !courseType.trim().equals("")) {
            //按照课程类型搜索
            sb.append("   and courseType = ?   ");
            params.add(courseType);
        }
        //查询符合条件的总条数
        List<Course> listEntity = dao.getListEntity(sb.toString(), Course.class, params.toArray());
        //总条数
        int totalCount =listEntity.size();
        pb.setTotalCount(totalCount);
        //总页数
        int totalPage = totalCount % size == 0 ? totalCount / size : totalCount / size + 1;
        pb.setTotalPage(totalPage);
        //查询符合条件的数据
        sb.append(" limit ?,? ");
        params.add((cpage-1)*size);
        params.add(size);
        List<Course> courseList = dao.getListEntity(sb.toString(), Course.class, params.toArray());
        pb.setList(courseList);
        return pb;
    }

    @Override
    public Course findCourse(String cid) {
        String sql = "select * from course where cid =?";
        //封装课程对象
        Course course = dao.getEntity(sql, Course.class, cid);
        //根据课程编号查询课程明细列表对象
        sql = "select * from coursedetail where cid =?";
        List<Coursedetail> coursedetailList = courseDetailDao.getListEntity(sql, Coursedetail.class, cid);
        course.setList(coursedetailList);
        return course;
    }
}
