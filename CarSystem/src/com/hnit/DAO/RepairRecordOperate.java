package com.hnit.DAO;

import com.hnit.util.CSUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ClassName: RepairRecordOperate
 * Description:
 *
 * @Author wzq
 * @Create 2024/6/11 15:55
 * @Version 1.0
 */
public class RepairRecordOperate {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;
    public RepairRecordOperate() {}

    //添加
    public int  addRepairRecord(int id,String number ,String project ,String submitdate,String completiondate,int EId ,int hours){
        String sql = "insert into repairrecord(recordid, platenumber, repairproject, submitdate, completiondate, employeeid, repairhours) VALUES(?,?,?,?,?,?,?)";
        int result = 0;
        conn = CSUtil.getConn();
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setObject(1,id);
            pstmt.setObject(2,number);
            pstmt.setObject(3,project);
            pstmt.setObject(4,submitdate);
            pstmt.setObject(5,completiondate);
            pstmt.setObject(6,EId);
            pstmt.setObject(7,hours);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("添加修理单信息异常："+e.getMessage());
            e.printStackTrace();
        }finally {
            CSUtil.closeAll(conn,pstmt,rs);
        }
        return result;
    }

    //删除
    public int deleteRepairRecord(int id){
        int result = 0;
        String sql = "delete from RepairRecord where RecordID = ?;";
        conn = CSUtil.getConn();
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setObject(1,id);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("删除修理信息异常："+e.getMessage());
        }finally {
            CSUtil.closeAll(conn,pstmt,rs);
        }
        return result;
    }

    public int updateRepairRecord(int id,String number,String project,String submit,String completion,int EID,int hour){
        int result = 0;
        String sql = "UPDATE RepairRecord SET PlateNumber  = ?,RepairProject = ?,SubmitDate = ?,CompletionDate = ?,EmployeeID = ?,RepairHours =? WHERE RecordID = ?;";
        conn = CSUtil.getConn();
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setObject(1,number);
            pstmt.setObject(2,project);
            pstmt.setObject(3,submit);
            pstmt.setObject(4,completion);
            pstmt.setObject(5,EID);
            pstmt.setObject(6,hour);
            pstmt.setObject(7,id);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("修改修理单信息异常："+e.getMessage());
        }finally {
            CSUtil.closeAll(conn,pstmt,rs);
        }
        return result;
    }

}
