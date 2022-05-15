package com.bjpn.util;

import java.sql.*;

public class JDBCUtil {
    //类加载的时候注册驱动
    static{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //获取数据库连接对象
    public static Connection getConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/sh2203?serverTimezone=UTC";
            Connection conn = DriverManager.getConnection(url, "root", "root");
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    //关闭
    public static void close(ResultSet resultSet, Statement statement, Connection conn) {
        try {
            if (resultSet!=null) resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (statement!=null) statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn!=null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
