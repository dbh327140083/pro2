package com.ujiuye.service.impl;

import com.ujiuye.dao.UserDao;
import com.ujiuye.entity.User;
import com.ujiuye.service.UserService;
import com.ujiuye.utils.PageBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserServiceImpl implements UserService {

    UserDao dao = new UserDao();

    @Override
    public User Login(String username, String password) {

        String sql = "select * from user where username =? and password =?";

        return dao.getEntity(sql,User.class,username,password);
    }

    @Override
    public PageBean<User> findByPage(String currentPage, String pageSize, String search) {

        PageBean<User> pb = new PageBean<>();
        int cpage = Integer.parseInt(currentPage);
        int size = Integer.parseInt(pageSize);
        pb.setCurrentPage(cpage);
        pb.setPageSize(size);
        //获取总条数
        StringBuilder sb = new StringBuilder("select * from user  where 1=1  ");
        //创建存储?占位符参数的集合
        ArrayList<Object> objects = new ArrayList<>();
        if (search!=null && !search.trim().equals("")) {
            sb.append(" and name like ?  ");
            objects.add("%" + search + "%");
        }
        //总条数
        List<User> listEntity = dao.getListEntity(sb.toString(), User.class, objects.toArray());
        int totalCount =listEntity.size();
        //总页数
        int totalPage = totalCount%size==0?totalCount/size:totalCount/size+1;
        pb.setTotalCount(totalCount);
        pb.setTotalPage(totalPage);

        sb.append(" limit ?,? ");

        objects.add((cpage - 1) * size);
        objects.add(size);

        List<User> userList = dao.getListEntity(sb.toString(), User.class, objects.toArray());
        pb.setList(userList);
        return pb;
    }

    @Override
    public int insertUser(User u) {

        String sql = "insert into user values(null,?,?,?,?,?,?,?,?,?,?) ";

        return dao.update(sql,u.getName(),u.getPhone(),u.getAge(),u.getSex(),u.getUsername(),u.getPassword(),u.getStatus(),new Date(),u.getRole(),u.getPicture());
    }

    @Override
    public int delAll(String ids) {

        String sql = "delete from user where uid in (" + ids + ")";

        return dao.update(sql);
    }

    @Override
    public int updateUser(User u) {
        String sql = "update user set name=?,phone=?,age=?,sex=?,username=?,password=?,status=?,createTime=?,role=?,picture=? where uid =?";
        Object arr[] ={u.getName(),u.getPhone(),u.getAge(),u.getStatus(),u.getUsername(),u.getPassword(),u.getStatus(),new Date(),u.getRole(),u.getPicture(),u.getUid()};
        return dao.update(sql,arr);
    }
}
