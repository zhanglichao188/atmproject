package com.bjpn.dao;

import com.bjpn.bean.ATM;
import com.bjpn.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ATMDao {
    //登入
    public ATM atmLogin(String atmCode, String atmPwd){
        Connection conn = null;
        PreparedStatement psta= null;
        ResultSet resultSet = null;
        try {
            //获取连接
            conn = JDBCUtil.getConnection();
            String sql = "select *  from atm where atm_code = ? and atm_pwd= ?";
            System.out.println("收起来语句："+sql);
            //创建预编译对象  传递sql
            psta = conn.prepareStatement(sql);
            psta.setString(1,atmCode);
            psta.setString(2,atmPwd);
            System.out.println("执行的sql："+psta);
            resultSet = psta.executeQuery();
            if(resultSet.next()){
                ATM atm = new ATM();
                atm.setAtmMoney(resultSet.getDouble("atm_money"));
                atm.setAtmPwd(resultSet.getString("atm_pwd"));
                atm.setAtmName(resultSet.getString("atm_name"));
                atm.setAtmId(resultSet.getInt("atm_id"));
                atm.setAtmCode(resultSet.getString("atm_code"));
                return atm;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            JDBCUtil.close(resultSet,psta,conn);
        }
        return null;
    }
    //注册方法
    public boolean atmReg(ATM atm) {
        Connection conn = null;
        PreparedStatement psat = null;
        try {
            if (atm.getAtmName()=="" || atm.getAtmPwd()=="") {
                return false;
            }
            conn = JDBCUtil.getConnection();
            String sql = "insert into atm (atm_code,atm_pwd,atm_name,atm_money) values(?,?,?,?)";
            System.out.println("注册的原始sql:"+sql);
            psat = conn.prepareStatement(sql);
            psat.setString(1, atm.getAtmCode());
            psat.setString(2, atm.getAtmPwd());
            psat.setString(3, atm.getAtmName());
            psat.setDouble(4, atm.getAtmMoney());
            System.out.println("注册拼接值后的sql语句:"+psat);
            int count = psat.executeUpdate();
            if (count!=-1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(null, psat, conn);
        }
        return false;
    }
    //根据卡号查询数据库中是否已经存在这个卡号
    public boolean checkCode(String atmCode) {
        Connection conn = null;
        PreparedStatement psat = null;
        ResultSet resultSet = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "select * from atm where atm_code=?";
            psat = conn.prepareStatement(sql);
            psat.setString(1, atmCode);
            resultSet = psat.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(resultSet, psat, conn);
        }
        return false;
    }
    //存款
    public boolean saveMoney(ATM atm) {
        Connection conn = null;
        PreparedStatement past = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "update atm set atm_money=atm_money+? where atm_code=?";
            past = conn.prepareStatement(sql);
            past.setDouble(1, atm.getAtmMoney());
            past.setString(2, atm.getAtmCode());
            System.out.println("存款拼接值后的sql语句:"+past);
            int count = past.executeUpdate();
            if (count!=-1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(null, past, conn);
        }
        return false;
    }
    //查询余额
    public double checkMoney(String atmCode) {
        Connection conn = null;
        PreparedStatement past = null;
        ResultSet resultSet = null;
        double atm_money=0;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "select * from atm where atm_code=?";
            past = conn.prepareStatement(sql);
            past.setString(1, atmCode);
            System.out.println(past);
            resultSet = past.executeQuery();
            if (resultSet.next()) {
                atm_money = resultSet.getDouble("atm_money");
            }
            System.out.println(atm_money);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(resultSet, past, conn);
        }
        return atm_money;
    }
    //取款
    public boolean drawMoney(String money,String atmCode) {
        Connection conn = null;
        PreparedStatement past = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "update atm set atm_money=atm_money-? where atm_code=?";
            past = conn.prepareStatement(sql);
            past.setDouble(1, Double.valueOf(money));
            past.setString(2, atmCode);
            System.out.println("取款拼接值后的sql语句:"+past);
            int count = past.executeUpdate();
            if (count!=-1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(null, past, conn);
        }
        return false;
    }
    //转账
    public boolean transferMoney(ATM atm,ATM atm1) {
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement past = null;
        try {
            conn.setAutoCommit(false);
            String sql = "update atm set atm_money=atm_money-? where atm_code=?";
            past = conn.prepareStatement(sql);
            past.setDouble(1, atm1.getAtmMoney());
            past.setString(2, atm.getAtmCode());
            past.executeUpdate();
            String sql2 = "update atm set atm_money=atm_money+? where atm_code=?";
            past = conn.prepareStatement(sql2);
            past.setDouble(1, atm1.getAtmMoney());
            past.setString(2, atm1.getAtmCode());
            past.executeUpdate();
            conn.commit();
            return true;
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            return false;
        } finally {
            JDBCUtil.close(null, past, conn);
        }
    }
}
