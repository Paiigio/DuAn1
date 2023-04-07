
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

    private HoaDonResponsitory hds = new HoaDonResponsitory();
    private CTSanPhamResponsitory ctspr = new CTSanPhamResponsitory();

    public ArrayList<HoaDonChiTiet> getAllHoaDonCT() {
        ArrayList<HoaDonChiTiet> list = new ArrayList<>();
        String sql = "SELECT* FROM dbo.HOADONCHITIET";
        ResultSet rs = JDBC_Helper.excuteQuery(sql);
        try {
            while (rs.next()) {
                HoaDon hd = hds.getHDByID(rs.getString(1));
                CTSanPham ctsp = ctspr.getCTSanPhamByID(rs.getString(2));
                list.add(new HoaDonChiTiet(rs.getString(9),hd, ctsp, rs.getFloat(3), rs.getInt(4), rs.getFloat(5), rs.getDate(6), rs.getDate(7),rs.getString(8)));
            }
        } catch (Exception ex) {

            ex.printStackTrace();
        }
        return list;
    }

    public HoaDonChiTiet insertHDCT(HoaDonChiTiet hdct) {
        String sql = "INSERT dbo.HOADONCHITIET(IDHD,IDCTSP,DONGIA,SOLUONG,NGAYTAO,NGAYSUA,GHICHU,THANHTIEN) VALUES(?,?,?,?,GETDATE(),null,?,?)";
        JDBC_Helper.excuteUpdate(sql, hdct.getIdhd().getId(), hdct.getIdctsp().getId(), hdct.getDongia(), hdct.getSl(),hdct.getGhiChu(),hdct.getThanhTien());
        return hdct;
    }

    public HoaDonChiTiet updateHDCT(HoaDonChiTiet hdct) {
        String sql = "Update dbo.HOADONCHITIET SET GHICHU =? WHERE IDHD = ?";
        JDBC_Helper.excuteUpdate(sql, hdct.getIdhd().getId(), hdct.getIdctsp().getId(), hdct.getDongia(), hdct.getSl());
        return hdct;
    }

    public Integer deleteHDCT(String id) {
        String sql = "DELETE HOADONCHITIET WHERE IDHDCT =?";
        int row = JDBC_Helper.excuteUpdate(sql, id);
        return row;
    }

    public ArrayList<HoaDonChiTiet> getAllHoaDonCTByIDHD(String id) {
        ArrayList<HoaDonChiTiet> list = new ArrayList<>();
        String sql = "SELECT* FROM dbo.HOADONCHITIET Where IDHD = ? ";
        ResultSet rs = JDBC_Helper.excuteQuery(sql, id);
        try {
            while (rs.next()) {
                HoaDon hd = hds.getHDByID(rs.getString(1));
                CTSanPham ctsp = ctspr.getCTSanPhamByID(rs.getString(2));
                list.add(new HoaDonChiTiet(rs.getString(9),hd, ctsp, rs.getFloat(3), rs.getInt(4), rs.getFloat(5), rs.getDate(6), rs.getDate(7),rs.getString(8)));
            }
        } catch (Exception ex) {

            ex.printStackTrace();
        }
        return list;
    }
    public Integer delete(String ghiChu , String idHD) {
        String sql = "DELETE HOADONCHITIET WHERE GHICHU =? AND IDHD = ?";
        int row = JDBC_Helper.excuteUpdate(sql, ghiChu , idHD);
        return row;
    }
    
}
