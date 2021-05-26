package com.ujiuye.dao.impl;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class BaseDao<T>  {

    ComboPooledDataSource dataSource= new ComboPooledDataSource();
    QueryRunner qr =new QueryRunner(dataSource);
    //参数1 要执行的sql 参数2 希望封装对象 参数3 sql语句中的参数
    public T getEntity(String sql,Class claz,Object...params){

        try {
            return qr.query(sql,new BeanHandler<T>(claz),params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<T> getListEntity(String sql, Class claz, Object... params) {

        try {
            return qr.query(sql, new BeanListHandler<T>(claz), params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public int update(String sql, Object... params) {

        try {
            return qr.update(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


}
