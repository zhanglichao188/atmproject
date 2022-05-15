package com.bjpn.dao;

import com.bjpn.bean.ATM;
import com.bjpn.util.JDBCUtil;

import java.sql.*;
import java.util.Random;

public class ATMDao {
    //登入验证
    public ATM login(String atmCode,String atmPwd) {
        Connection conn = null;
        PreparedStatement psta = null;
        ResultSet resultSet = null;
        try {
            //通过工具类获得数据库连接对象
            conn = JDBCUtil.getConnection();
            String sql = "select * from atm where atm_code=? and atm_pwd=?";
            //获得发送sql语句预编译对象
            psta = conn.prepareStatement(sql);
            //给占位符赋值
            psta.setString(1, atmCode);
            psta.setString(2, atmPwd);
            resultSet = psta.executeQuery();
            //如果有这一条数据，把数据封装成一个ATM对象返回
            if (resultSet.next()) {
                ATM atm = new ATM();
                atm.setAtm_id(resultSet.getInt("atm_id"));
                atm.setAtm_code(resultSet.getString("atm_code"));
                atm.setAtm_name(resultSet.getString("atm_name"));
                atm.setAtm_pwd(resultSet.getString("atm_pwd"));
                atm.setAtm_money(resultSet.getDouble("atm_money"));
                return atm;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(resultSet, psta, conn);
        }
        return null;
    }
    //注册方法，往数据库插入数据
    public int reg(String atm_name,String atm_pwd) {
        Connection conn = null;
        PreparedStatement psta = null;
        int count=0;
        try {
            conn = JDBCUtil.getConnection();
            //如果用户名和用户密码为空，则注册失败
            if ("".equals(atm_name)||"".equals(atm_pwd)) {
                return -1;
            }
            String sql = "insert into atm values(null, ?,null,? ,0)";
            psta = conn.prepareStatement(sql);
            //先把用户名和密码插入表中
            psta.setString(1, atm_name);
            psta.setString(2, atm_pwd);
            count = psta.executeUpdate();
            //自动生成不重复的卡号
            String atmCode="";
            //前五位为随机数
            for (int i = 0; i < 5; i++) {
                atmCode+=new Random().nextInt(10);
            }
            //查询最近更新的一条数据
            String sql2 = "select * from atm order by atm_id desc limit 0,1";
            psta = conn.prepareStatement(sql2);
            ResultSet resultSet = psta.executeQuery();
            int atm_id =0;
            //取出atm表最新插入的一条记录的id字段拼接到五位随机数后面
            if (resultSet.next()) {
                atm_id = resultSet.getInt("atm_id");
                atmCode = atmCode + atm_id;
            }
            //更新code字段
            String sql3 = "update atm set atm_code=? where atm_id=?";
            psta = conn.prepareStatement(sql3);
            psta.setString(1, atmCode);
            psta.setString(2, atm_id+"");
            psta.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(null, psta, conn);
        }
        return count;
    }
}
