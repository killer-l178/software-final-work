package com.hnit.DAO;

import com.hnit.util.CSUtil;
import java.sql.*;

/**
 * ClassName: EmployeesOperate
 * Description:
 *
 * @Author wzq
 * @Create 2024/6/11 15:53
 * @Version 1.0
 */

//�������ݿ���ز���
public class EmployeesOperate {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;
    public EmployeesOperate() {}

    //���
    public int  addEmployees(int id,String name ,String address,String phone,String birth,String entry,int wage){
        String sql = "insert into Employee(EmployeeID, EmployeeName, EmployeeAddress, EmployeePhone, BirthDate, EntryDate, HourlyWage) VALUES(?,?,?,?,?,?,?)";
        int result = 0;
        conn = CSUtil.getConn();
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setObject(1,id);
            pstmt.setObject(2,name);
            pstmt.setObject(3,address);
            pstmt.setObject(4,phone);
            pstmt.setObject(5,birth);
            pstmt.setObject(6,entry);
            pstmt.setObject(7,wage);

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("���������Ϣ�쳣��"+e.getMessage());
            e.printStackTrace();
        }finally {
            CSUtil.closeAll(conn,pstmt,rs);
        }
        return result;
    }

    //ɾ��
    public int deleteEmployees(int id){
        int result = 0;
        String sql = "delete from Employee where EmployeeID = ?;";
        conn = CSUtil.getConn();
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setObject(1,id);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ɾ��������Ϣ�쳣��"+e.getMessage());
        }finally {
            CSUtil.closeAll(conn,pstmt,rs);
        }
        return result;
    }

    //�޸�
    public int updateEmployees(int id,String name,String address,String phone,String birth,String entry,int wage){
        int result = 0;
        String sql = "UPDATE Employee SET EmployeeName = ?,EmployeeAddress = ?,EmployeePhone = ?,BirthDate = ?,EntryDate = ?,HourlyWage = ? WHERE EmployeeID =?;";
        conn = CSUtil.getConn();
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setObject(1,name);
            pstmt.setObject(2,address);
            pstmt.setObject(3,phone);
            pstmt.setObject(4,birth);
            pstmt.setObject(5,entry);
            pstmt.setObject(6,wage);
            pstmt.setObject(7,id);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("�޸�������Ϣ�쳣��"+e.getMessage());
        }finally {
            CSUtil.closeAll(conn,pstmt,rs);
        }
        return result;
    }

}
