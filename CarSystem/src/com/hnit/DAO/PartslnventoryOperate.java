package com.hnit.DAO;

import com.hnit.util.CSUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ClassName: PartslnventoryOperate
 * Description:
 *
 * @Author wzq
 * @Create 2024/6/11 15:54
 * @Version 1.0
 */
public class PartslnventoryOperate {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;
    public PartslnventoryOperate() {}


    public int  addPartslnventory(String number,String name ,int cost,int price,int stock,int minstock,int order){
        String sql = "insert into partsinventory(partnumber, partname, cost, price, stockquantity, minstock, orderquantity) VALUE(?,?,?,?,?,?,?)";
        int result = 0;
        conn = CSUtil.getConn();
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setObject(1,number);
            pstmt.setObject(2,name);
            pstmt.setObject(3,cost);
            pstmt.setObject(4,price);
            pstmt.setObject(5,stock);
            pstmt.setObject(6,minstock);
            pstmt.setObject(7,order);


            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("添加零件库存信息异常："+e.getMessage());
            e.printStackTrace();
        }finally {
            CSUtil.closeAll(conn,pstmt,rs);
        }
        return result;
    }

    //删除
    public int deletePartslnventory(String number){
        int result = 0;
        String sql = "delete from PartsInventory where PartNumber like ?;";
        conn = CSUtil.getConn();
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setObject(1,number);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("删除修理信息异常："+e.getMessage());
        }finally {
            CSUtil.closeAll(conn,pstmt,rs);
        }
        return result;
    }

    public int updatePartInventory(String name,int cost,int price,int stock,int minStock,int order,String number){
        int result = 0;
        String sql = "UPDATE PartsInventory SET PartName = ?,Cost = ?,Price = ?,StockQuantity = ?,MinStock = ?,OrderQuantity = ? WHERE PartNumber = ?;";
        conn = CSUtil.getConn();
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setObject(1,name);
            pstmt.setObject(2,cost);
            pstmt.setObject(3,price);
            pstmt.setObject(4,stock);
            pstmt.setObject(5,minStock);
            pstmt.setObject(6,order);
            pstmt.setObject(7,number);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("修改零件库存信息异常："+e.getMessage());
        }finally {
            CSUtil.closeAll(conn,pstmt,rs);
        }
        return result;
    }

}
