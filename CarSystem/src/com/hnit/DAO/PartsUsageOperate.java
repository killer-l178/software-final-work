package com.hnit.DAO;

import com.hnit.util.CSUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ClassName: PartsUsageOperate
 * Description:
 *
 * @Author wzq
 * @Create 2024/6/11 15:54
 * @Version 1.0
 */
public class PartsUsageOperate {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;
    public PartsUsageOperate() {}

    public int  addPartsUsage(int id,String number,int quantity){
        String sql = "insert into PartsUsage(UsageID, PartNumber, Quantity) VALUE(?,?,?)";
        int result = 0;
        conn = CSUtil.getConn();
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setObject(1,id);
            pstmt.setObject(2,number);
            pstmt.setObject(3,quantity);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("添加零件信息异常："+e.getMessage());
            e.printStackTrace();
        }finally {
            CSUtil.closeAll(conn,pstmt,rs);
        }
        return result;
    }
    //删除
    public int deletePartsUsage(int id){
        int result = 0;
        String sql = "delete from PartsUsage where UsageID = ?;";
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

    public int updatePartsUsage(int id,String number,int quantity){
        int result = 0;
        String sql = "UPDATE PartsUsage SET PartNumber = ?,Quantity = ? WHERE UsageID = ?;";
        conn = CSUtil.getConn();
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setObject(1,number);
            pstmt.setObject(2,quantity);
            pstmt.setObject(3,id);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("修改零件数量信息异常："+e.getMessage());
        }finally {
            CSUtil.closeAll(conn,pstmt,rs);
        }
        return result;
    }

}
