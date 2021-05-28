package com.ujiuye.service.impl;

import com.ujiuye.dao.CourseDao;
import com.ujiuye.dao.CourseUserDao;
import com.ujiuye.dao.UserDao;
import com.ujiuye.entity.Course;
import com.ujiuye.entity.CourseUser;
import com.ujiuye.entity.User;
import com.ujiuye.service.CourseUserService;
import com.ujiuye.utils.PageBean;

import java.util.List;

public class CourseUserServiceImpl implements CourseUserService {
    CourseUserDao dao= new CourseUserDao();
    UserDao userDao = new UserDao();
    CourseDao courseDao = new CourseDao();
    @Override
    public PageBean<CourseUser> findAllByPage(String currentPage, String pageSize) {

        PageBean<CourseUser> pb = new PageBean<>();

        int cpage = Integer.parseInt(currentPage);
        int size = Integer.parseInt(pageSize);
        pb.setCurrentPage(cpage);
        pb.setPageSize(size);

        //查询总数
        String sql = "select * from  course_user";

        List<CourseUser> users = dao.getListEntity(sql, CourseUser.class);
        //总记录数
        int totalCount = users.size();
        pb.setTotalCount(totalCount);
        //总页数
        int totalPage = totalCount%size==0?totalCount/size:totalCount/size+1;
        pb.setTotalPage(totalPage);
        //用户想看的数据
        sql = "select * from course_user limit ?,?";
        List<CourseUser> courseUserList = dao.getListEntity(sql, CourseUser.class, (cpage - 1) * size, size);
        //每一个用户课程对象中  都有用户Uid和课程cid
        for (CourseUser courseUser : courseUserList) {
            String sql1 = "select * from user where uid =?";
            User user = userDao.getEntity(sql1, User.class, courseUser.getUid());
            courseUser.setUser(user);
            String sql2 = "select * from course where cid =?";
            Course course = courseDao.getEntity(sql2, Course.class, courseUser.getCid());
            courseUser.setCourse(course);
        }
        pb.setList(courseUserList);
        return pb;
    }

    @Override
    public int updateCourseUser(String cid, String id) {

        String sql = "update course_user set cid =? where id =?";
        return dao.update(sql,cid,id);
    }
}
