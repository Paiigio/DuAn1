/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsitory;

import DomainModels.CTSanPham;
import DomainModels.ChucVu;
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
public class CTSanPhamResponsitory {
    MauSacResponsitory ms = new MauSacResponsitory();
    
        public ArrayList<CTSanPham> getAllCTSanPham() {
        ArrayList<CTSanPham> list = new ArrayList<>();
        String sql="SELECT* FROM dbo.CTSP";
        ResultSet rs = JDBC_Helper.excuteQuery(sql);
        try {
            while (rs.next()) {
                list.add(new CTSanPham(sql, ms, ctkm, sp, dl, sql, sql, 0, sql, 0, 0, 0, ngayTao, ngayNhap));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(CTSanPhamResponsitory.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return list;
    }
       public CTSanPham getCTSanPhamByID(String id){
        
        String sql="SELECT * FROM CHUCVU WHERE IDCV=?";
        ResultSet rs=JDBC_Helper.excuteQuery(sql,id);
        try {
            while(rs.next()){
                return new ChucVu(rs.getString(1),rs.getString(2),rs.getString(3),rs.getDate(4),rs.getDate(5));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CTSanPhamResponsitory.class.getName()).log(Level.SEVERE, null, ex);
        }
       return null;
    }
    public CTSanPham insertCTSanPham(CTSanPham ctsp){
        String sql= "INSERT INTO CHUCVU VALUES(NEWID(),?,?,GETDATE(),GETDATE())";
       JDBC_Helper.excuteUpdate(sql, cv.getMa(),cv.getTenCV());
        return cv;
    }
    public CTSanPham updateCTSanPham(CTSanPham ctsp){
        String sql= "UPDATE dbo.ChucVu SET Tencv=?,NGAYSUA=GETDATE() WHERE MACV=?";
       JDBC_Helper.excuteUpdate(sql, cv.getTenCV(),cv.getMa());
        return cv;
    }
    public Integer deleteCTSanPham(String ma){
        String sql="DELETE dbo.ChucVu WHERE maCV =?";
        int row=JDBC_Helper.excuteUpdate(sql,ma);
        return row;
    }
  
}
