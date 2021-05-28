package com.ujiuye.service.impl;

import com.ujiuye.dao.CourseDao;
import com.ujiuye.entity.Course;
import com.ujiuye.entity.Coursedetail;
import com.ujiuye.service.CourseService;
import com.ujiuye.utils.PageBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CourseServiceImpl implements CourseService {

    CourseDao dao= new CourseDao();

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
}
