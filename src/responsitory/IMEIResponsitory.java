/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsitory;

import DomainModels.CTSanPham;
import DomainModels.IMEI;
import Utilites.JDBC_Helper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author duong
 */
public class IMEIResponsitory {

    CTSanPhamResponsitory c = new CTSanPhamResponsitory();

    public ArrayList<IMEI> getAllIMEI() {
        ArrayList<IMEI> list = new ArrayList<>();
        String sql = "SELECT * FROM dbo.IMEI";
        ResultSet rs = JDBC_Helper.excuteQuery(sql);
        try {
            while (rs.next()) {
                CTSanPham ctsp = c.getCTSanPhamByID(rs.getString(6));
                list.add(new IMEI(rs.getString(1), ctsp, rs.getString(2), rs.getDate(3), rs.getString(4), rs.getInt(5)));

            }
        } catch (SQLException ex) {
            Logger.getLogger(IMEIResponsitory.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return list;
    }

    public IMEI getIMEIByID(String id) {

        String sql = "SELECT * FROM IMEI WHERE IDIMEI=?";
        ResultSet rs = JDBC_Helper.excuteQuery(sql, id);
        try {
            while (rs.next()) {
                CTSanPham ctsp = c.getCTSanPhamByID(rs.getString(6));
                return new IMEI(rs.getString(1), ctsp, rs.getString(3), rs.getDate(3), rs.getString(4), rs.getInt(5));
            }
        } catch (SQLException ex) {
            Logger.getLogger(IMEIResponsitory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<IMEI> getIMEIByNote(String note) {
        ArrayList<IMEI> list = new ArrayList<>();
        String sql = "SELECT * FROM IMEI WHERE ghiChu=?";
        ResultSet rs = JDBC_Helper.excuteQuery(sql, note);
        try {
            while (rs.next()) {
                CTSanPham ctsp = c.getCTSanPhamByID(rs.getString(6));
                System.out.println(ctsp);
                list.add(new IMEI(rs.getString(1), ctsp, rs.getString(2), rs.getDate(3), rs.getString(4), rs.getInt(5)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(IMEIResponsitory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public IMEI insertIMEI(IMEI i) {
        String sql = "INSERT INTO IMEI VALUES(NEWID(),?,GETDATE(),?,?,?)";
        JDBC_Helper.excuteUpdate(sql, i.getMa(), i.getGhiChu(), i.getTrangThai(), i.getCtsp().getId());
        return i;
    }

    public IMEI updateIMEI(IMEI i) {
        String sql = "UPDATE dbo.IMEI SET GHICHU=?,TRANGTHAI=? WHERE MAIMEI=?";
        JDBC_Helper.excuteUpdate(sql, i.getGhiChu(), i.getTrangThai(), i.getMa());
        return i;
    }

    public Integer updateIMEI_ThanhToan(String ma) {
        String sql = "UPDATE dbo.IMEI SET TRANGTHAI=1 WHERE MAIMEI=?";
        int row = JDBC_Helper.excuteUpdate(sql, ma);
        return row;
    }

    public Integer deleteIMEI(String ma) {
        String sql = "DELETE dbo.IMEI WHERE MAIMEI =?";
        int row = JDBC_Helper.excuteUpdate(sql, ma);
        return row;
    }

    public ArrayList<IMEI> selectSL(String id) {
        ArrayList<IMEI> list = new ArrayList<>();
        String sql = "SELECT * FROM dbo.IMEI WHERE IDCTSP=? And TrangThai = 0";
        ResultSet rs = JDBC_Helper.excuteQuery(sql, id);
        try {
            while (rs.next()) {
                CTSanPham ctsp = c.getCTSanPhamByID(rs.getString(6));
                System.out.println(ctsp);
                list.add(new IMEI(rs.getString(1), ctsp, rs.getString(2), rs.getDate(3), rs.getString(4), rs.getInt(5)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(IMEIResponsitory.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return list;
    }

    public IMEI getTrangThaiByIMEI(String imei) {

        String sql = "SELECT * FROM IMEI WHERE MAIMEI=?";
        ResultSet rs = JDBC_Helper.excuteQuery(sql, imei);
        try {
            while (rs.next()) {
                CTSanPham ctsp = c.getCTSanPhamByID(rs.getString(6));
                return new IMEI(rs.getString(1), ctsp, rs.getString(3), rs.getDate(3), rs.getString(4), rs.getInt(5));
            }
        } catch (SQLException ex) {
            Logger.getLogger(IMEIResponsitory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
        public Integer updateIMEI_HuyHang(String ma) {
        String sql = "UPDATE dbo.IMEI SET TRANGTHAI=0 WHERE MAIMEI=?";
        int row = JDBC_Helper.excuteUpdate(sql, ma);
        return row;
    }
    
}
