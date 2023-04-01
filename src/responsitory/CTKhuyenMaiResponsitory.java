/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsitory;

import DomainModels.CTKhuyenMai;
import Utilites.JDBC_Helper;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author duong
 */
public class CTKhuyenMaiResponsitory {

    public ArrayList<CTKhuyenMai> getAllCTKM() {
        ArrayList<CTKhuyenMai> list = new ArrayList<>();
        String sql = "SELECT* FROM dbo.CTKHUYENMAI";
        ResultSet rs = JDBC_Helper.excuteQuery(sql);
        try {
            while (rs.next()) {
                list.add(new CTKhuyenMai(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getDate(5),
                        rs.getString(6), rs.getDate(7), rs.getDate(8), rs.getInt(9)));

            }
        } catch (SQLException ex) {

            ex.printStackTrace();
        }
        return list;
    }

    public CTKhuyenMai getCVCTKMID(String id) {

        String sql = "SELECT * FROM CTKHUYENMAI WHERE IDCTKM=?";
        ResultSet rs = JDBC_Helper.excuteQuery(sql, id);
        try {
            while (rs.next()) {
                return new CTKhuyenMai(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getDate(5),
                        rs.getString(6), rs.getDate(7), rs.getDate(8), rs.getInt(9));
            }
        } catch (SQLException ex) {

        }
        return null;
    }

    public CTKhuyenMai insertCTKM(CTKhuyenMai cv) {
        String sql = "	INSERT INTO dbo.CTKHUYENMAI(IDCTKM,  MAKM, TENKM,  TGBATDAU,TGKETTHUC,HINHTHUC,"
                + "NGAYTAO,NGAYSUA)VALUES(NEWID(),?,?,?,?,?,GETDATE(),NULL)";
        JDBC_Helper.excuteUpdate(sql, cv.getMa(), cv.getTen(), cv.getThoiGianBatDau(), cv.getThoiGianKetThuc(), cv.getHinhThuc());
        return cv;
    }

    public CTKhuyenMai updateCTKM(CTKhuyenMai cv) {
        String sql = "UPDATE dbo.CTKHUYENMAI SET TENKM =?,TGBATDAU=?,TGKETTHUC=?,HINHTHUC=?,NGAYSUA=GETDATE() WHERE MAKM=?";
        JDBC_Helper.excuteUpdate(sql, cv.getTen(), cv.getThoiGianBatDau(), cv.getThoiGianKetThuc(), cv.getHinhThuc(), cv.getMa());
        return cv;
    }
    
    public CTKhuyenMai updateTrangThai(CTKhuyenMai cv) {
        String sql = "UPDATE CTKHUYENMAI SET TRANGTHAI = 1 WHERE TGKETTHUC = ?";
        JDBC_Helper.excuteUpdate(sql, cv.getThoiGianKetThuc());
        return cv;
    }
    
    public ArrayList<CTKhuyenMai> getTimTen(String ten) {
        ArrayList<CTKhuyenMai> list = new ArrayList<>();
        String sql = "SELECT * FROM dbo.CTKHUYENMAI where tenkm like ? ";
        ResultSet rs = JDBC_Helper.excuteQuery(sql, "%" + ten + "%");
        try {
            while (rs.next()) {
                list.add(new CTKhuyenMai(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getDate(5),
                        rs.getString(6), rs.getDate(7), rs.getDate(8), rs.getInt(9)));

            }
        } catch (SQLException ex) {

        }
        return list;
    }
}
