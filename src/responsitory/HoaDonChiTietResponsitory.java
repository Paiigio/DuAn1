/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsitory;

import DomainModels.CTSanPham;
import DomainModels.HoaDon;
import DomainModels.HoaDonChiTiet;
import Utilites.JDBC_Helper;
import java.util.ArrayList;
import java.sql.*;
/**
 *
 * @author duong
 */

public class HoaDonChiTietResponsitory {
    private HoaDonResponsitory hds= new HoaDonResponsitory();
    private CTSanPhamResponsitory ctspr=new CTSanPhamResponsitory();
     public ArrayList<HoaDonChiTiet> getAllHoaDonCT() {
        ArrayList<HoaDonChiTiet> list = new ArrayList<>();
        String sql = "SELECT* FROM dbo.HOADONCHITIET";
        ResultSet rs = JDBC_Helper.excuteQuery(sql);
        try {
            while (rs.next()) {
                HoaDon hd=hds.getHDByID(rs.getString(1));
                CTSanPham ctsp = ctspr.getCTSanPhamByID(rs.getString(2));
                list.add(new HoaDonChiTiet(hd, ctsp, rs.getFloat(3), rs.getInt(4), rs.getFloat(5), rs.getDate(6), rs.getDate(7)));
            }
        } catch (Exception ex) {

            ex.printStackTrace();
        }
        return list;
 }
    public HoaDonChiTiet insertHDCT(HoaDonChiTiet hdct) {
        String sql = "INSERT dbo.HOADONCHITIET(IDHD,IDCTSP,DONGIA,SOLUONG,NGAYTAO,NGAYSUA) VALUES(?,?,?,?,GETDATE(),null)";
        JDBC_Helper.excuteUpdate(sql, hdct.getIdhd().getId(),hdct.getIdctsp().getId(),hdct.getDongia(),hdct.getSl());
        return hdct;
    }
    public Integer deleteHDCT(String ma) {
        String sql = "DELETE HOADONCHITIET WHERE IDCTSP =?";
        int row = JDBC_Helper.excuteUpdate(sql, ma);
        return row;
    }
         public ArrayList<HoaDonChiTiet> getAllHoaDonCTByIDHD(String id) {
        ArrayList<HoaDonChiTiet> list = new ArrayList<>();
        String sql = "SELECT* FROM dbo.HOADONCHITIET Where IDHD = ? ";
        ResultSet rs = JDBC_Helper.excuteQuery(sql,id);
        try {
            while (rs.next()) {
                HoaDon hd=hds.getHDByID(rs.getString(1));
                CTSanPham ctsp = ctspr.getCTSanPhamByID(rs.getString(2));
                list.add(new HoaDonChiTiet(hd, ctsp, rs.getFloat(3), rs.getInt(4), rs.getFloat(5), rs.getDate(6), rs.getDate(7)));
            }
        } catch (Exception ex) {

            ex.printStackTrace();
        }
        return list;
        }
}
