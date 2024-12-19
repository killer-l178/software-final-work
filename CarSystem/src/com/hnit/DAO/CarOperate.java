package com.hnit.DAO;

import com.hnit.util.CSUtil;
import java.sql.*;

/**
 * ClassName: CarOperate
 * Description:
 *
 * @Author wzq
 * @Create 2024/6/11 15:52
 * @Version 1.0
 */
public class CarOperate {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;
    public CarOperate() {}

    //添加信息
    public int  addCar(String number,String model,String manufacturer,String ownerName){
        String sql = "insert into car(PlateNumber, Model, Manufacturer, OwnerName) VALUE(?,?,?,?)";
        int result = 0;
        conn = CSUtil.getConn();
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setObject(1,number);
            pstmt.setObject(2,model);
            pstmt.setObject(3,manufacturer);
            pstmt.setObject(4,ownerName);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("添加车辆信息异常："+e.getMessage());
            e.printStackTrace();
        }finally {
            CSUtil.closeAll(conn,pstmt,rs);
        }
        return result;
    }
    //删除信息
    public int deleteCar(String number){
        int result = 0;
        String sql = "delete from Car where PlateNumber like ?;";
        conn = CSUtil.getConn();
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setObject(1,number);
            result = pstmt.executeUpdate();
            //System.out.println("删除成功");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("删除车辆信息异常："+e.getMessage());
        }finally {
            CSUtil.closeAll(conn,pstmt,rs);
        }
        return result;
    }
    //修改表
    public int updateCar(String number,String model,String manufacturer,String name){
        int result = 0;
        String sql = "UPDATE Car SET Model = ?,Manufacturer = ?,OwnerName=? where PlateNumber like ?;";
        conn = CSUtil.getConn();
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setObject(1,model);
            pstmt.setObject(2,manufacturer);
            pstmt.setObject(3,name);
            pstmt.setObject(4,number);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("修改车辆信息异常："+e.getMessage());
        }finally {
            CSUtil.closeAll(conn,pstmt,rs);
        }
        return result;
    }
}
