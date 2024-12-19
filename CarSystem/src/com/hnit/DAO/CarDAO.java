package com.hnit.DAO;

import com.hnit.util.CSUtil;

import java.sql.*;
import java.util.Vector;

/**
 * ClassName: CarDAO
 * Description:
 *
 * @Author wzq
 * @Create 2024/6/6 20:53
 * @Version 1.0
 */
public class CarDAO {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;
    //用户注册
    public int Enroll(String name,String pwd){
        String sql = "select *from Manager where UserName like ? ;";
        conn = CSUtil.getConn();
        int result = 0;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setObject(1,name);
            ResultSet resultSet = pstmt.executeQuery();
            if(resultSet.next()){
                result = 0;
            }else{
                result = 1;
                String sql_1 = "insert into Manager(username, userpwd) VALUE(?,?);";
                pstmt = conn.prepareStatement(sql_1);
                pstmt.setObject(1,name);
                pstmt.setObject(2,pwd);
                int i = pstmt.executeUpdate();
                if(i>0)result = 1;
                else result =0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            CSUtil.closeAll(conn,pstmt,rs);
        }
        return result;
    }

    //验证用户登录
    public int VerifyLogIn(String name,String pwd){
        String sql = "select *from Manager where UserName like ? and UserPWD like ? ;";
        conn = CSUtil.getConn();
        int result = 0;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setObject(1,name);
            pstmt.setObject(2,pwd);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()){
                result = 1;
            }
            else {
                result = 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            CSUtil.closeAll(conn,pstmt,rs);
        }
        return result;
    }

    //将数据库中的数据添加到表格中
    public Vector<Vector<Object>> Fetch(Object tableName){
        String sql = "select *from "+tableName+";";
        Vector<Object> title = new Vector<>();
        Vector<Vector<Object>> data  = new Vector<Vector<Object>>();
        try {
            conn = CSUtil.getConn();
            pstmt = conn.prepareStatement(sql);
            //解析查询结果
            rs = pstmt.executeQuery();
            //获取字段名
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            //解析返回结果
            while(rs.next()){
                Vector<Object> v = new Vector<>();
                for (int i = 1; i <=columnCount ; i++) {
                    title.add(metaData.getColumnLabel(i));
                    v.add(rs.getObject(i));
                }
                data.add(v);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            CSUtil.closeAll(conn,pstmt,rs);

        }
        return data;
    }

    //查询操作
    public Vector<Vector<Object>> find(String sql,String input){
        Vector<Object> title = new Vector<>();
        Vector<Vector<Object>> data  = new Vector<Vector<Object>>();
        conn = CSUtil.getConn();
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setObject(1,"%"+input+"%");
            //解析查询结果
            rs = pstmt.executeQuery();
            //获取字段名
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            //解析返回结果
            while(rs.next()){
                Vector<Object> v = new Vector<>();
                for (int i = 1; i <=columnCount ; i++) {
                    title.add(metaData.getColumnLabel(i));
                    v.add(rs.getObject(i));
                }
                data.add(v);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            CSUtil.closeAll(conn,pstmt,rs);
        }
        return data;
    }

    //查询所有发票信息
    public Vector<Vector<Object>> print(String sql){
        Vector<Object> title = new Vector<>();
        Vector<Vector<Object>> data  = new Vector<Vector<Object>>();
        conn = CSUtil.getConn();
        try {
            pstmt = conn.prepareStatement(sql);
            //解析查询结果
            rs = pstmt.executeQuery();
            //获取字段名
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            //解析返回结果
            while(rs.next()){
                Vector<Object> v = new Vector<>();
                for (int i = 1; i <=columnCount ; i++) {
                    title.add(metaData.getColumnLabel(i));
                    v.add(rs.getObject(i));
                }
                data.add(v);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            CSUtil.closeAll(conn,pstmt,rs);
        }
        return data;
    }

    //更新工资表
    public int update(){
        int result = 0;
        String sql = "UPDATE Salary SET Performance = (SELECT SUM(RepairHours * HourlyWage)\n" +
                "FROM Employee, RepairRecord WHERE Employee.EmployeeID = RepairRecord.EmployeeID\n" +
                "AND Salary.EmployeeID = Employee.EmployeeID);";
        conn = CSUtil.getConn();
        try {
            pstmt = conn.prepareStatement(sql);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("更新工资信息异常："+e.getMessage());
        }finally {
            CSUtil.closeAll(conn,pstmt,rs);
        }
        return result;
    }
}
