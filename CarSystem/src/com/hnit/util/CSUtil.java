package com.hnit.util;

import java.sql.*;

/**
 * ClassName: CSUtil
 * Description:
 *
 * @Author wzq
 * @Create 2024/6/6 20:52
 * @Version 1.0
 */
public class CSUtil {
    //��������
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    //��ȡ���Ӷ���
    public static Connection getConn(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql:///car_repair","root","123456");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    //�ر���Դ
    public static void closeAll(Connection conn, PreparedStatement pstmt, ResultSet rs){
        if(null!=rs){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(null!=pstmt){
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

