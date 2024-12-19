package com.hnit.DAO;


import com.hnit.util.CSUtil;
import java.sql.*;


/**
 * ClassName: CarOwnerOperate
 * Description:
 *
 * @Author wzq
 * @Create 2024/6/11 15:53
 * @Version 1.0
 */
public class CarOwnerOperate {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;
    public CarOwnerOperate() {}

    //�������
    public int  addCarOwner(String name,String address,String phone){
        String sql = "insert into CarOwner(OwnerName, CarOwnerAddress, CarOwnerPhone) VALUES(?,?,?)";
        int result = 0;
        conn = CSUtil.getConn();
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setObject(1,name);
            pstmt.setObject(2,address);
            pstmt.setObject(3,phone);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("��ӳ�����Ϣ�쳣��"+e.getMessage());
            e.printStackTrace();
        }finally {
            CSUtil.closeAll(conn,pstmt,rs);
        }
        return result;
    }

    //ɾ��ָ������
    public int deleteCarOwner(String name){
        int result = 0;
        String sql = "delete from CarOwner where OwnerName like ?;";
        conn = CSUtil.getConn();
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setObject(1,name);
            result = pstmt.executeUpdate();
            //System.out.println("ɾ���ɹ�");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ɾ��������Ϣ�쳣��"+e.getMessage());
        }finally {
            CSUtil.closeAll(conn,pstmt,rs);
        }
        return result;
    }

    //�޸ı�
    public int updateCarOwner(String name,String address,String phone){
        int result = 0;
        String sql = "UPDATE CarOwner SET CarOwnerPhone = ?,CarOwnerAddress = ? WHERE OwnerName like ?;;";
        conn = CSUtil.getConn();
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setObject(1,phone);
            pstmt.setObject(2,address);
            pstmt.setObject(3,name);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("�޸ĳ�����Ϣ�쳣��"+e.getMessage());
        }finally {
            CSUtil.closeAll(conn,pstmt,rs);
        }
        return result;
    }

}
