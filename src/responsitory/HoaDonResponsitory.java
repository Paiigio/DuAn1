package responsitory;

import DomainModels.Coupon;
import DomainModels.HoaDon;
import DomainModels.KhachHang;
import DomainModels.NhanVien;
import Utilites.JDBC_Helper;
import java.util.ArrayList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author duong
 */
public class HoaDonResponsitory {

    private KhachHangResponsitory khr = new KhachHangResponsitory();
    private NhanVienResponsitory nvr = new NhanVienResponsitory();
    private CouponReponsitory cr = new CouponReponsitory();

    public ArrayList<HoaDon> getAllHoaDon() {
        ArrayList<HoaDon> list = new ArrayList<>();
        String sql = "SELECT* FROM dbo.HoaDon";
        ResultSet rs = JDBC_Helper.excuteQuery(sql);
        try {
            while (rs.next()) {
                KhachHang kh = khr.getKHByID(rs.getString(2));
                NhanVien nv = nvr.getNVByID(rs.getString(3));
                Coupon cp = cr.getCPByID(rs.getString(4));
                list.add(new HoaDon(rs.getString(1), kh, nv, cp, rs.getString(5), rs.getFloat(6), rs.getInt(7), rs.getDate(8), rs.getInt(9),
                        rs.getDate(10), rs.getDate(11), rs.getString(12)));
            }
        } catch (Exception ex) {

            ex.printStackTrace();
        }
        return list;
    }

    public ArrayList<HoaDon> getAllHoaDonTT() {
        ArrayList<HoaDon> list = new ArrayList<>();
        String sql = "SELECT* FROM dbo.HoaDon where TRANGTHAI =1 ";
        ResultSet rs = JDBC_Helper.excuteQuery(sql);
        try {
            while (rs.next()) {
                KhachHang kh = khr.getKHByID(rs.getString(2));
                NhanVien nv = nvr.getNVByID(rs.getString(3));
                Coupon cp = cr.getCPByID(rs.getString(4));
                list.add(new HoaDon(rs.getString(1), kh, nv, cp, rs.getString(5), rs.getFloat(6), rs.getInt(7), rs.getDate(8), rs.getInt(9),
                        rs.getDate(10), rs.getDate(11), rs.getString(12)));
            }
        } catch (Exception ex) {

            ex.printStackTrace();
        }
        return list;
    }

    public ArrayList<HoaDon> getAllHoaDonCTT() {
        ArrayList<HoaDon> list = new ArrayList<>();
        String sql = "SELECT* FROM dbo.HoaDon where TRANGTHAI =0 ";
        ResultSet rs = JDBC_Helper.excuteQuery(sql);
        try {
            while (rs.next()) {
                KhachHang kh = khr.getKHByID(rs.getString(2));
                NhanVien nv = nvr.getNVByID(rs.getString(3));
                Coupon cp = cr.getCPByID(rs.getString(4));
                list.add(new HoaDon(rs.getString(1), kh, nv, cp, rs.getString(5), rs.getFloat(6), rs.getInt(7), rs.getDate(8), rs.getInt(9),
                        rs.getDate(10), rs.getDate(11), rs.getString(12)));
            }
        } catch (Exception ex) {

            ex.printStackTrace();
        }
        return list;
    }

    public HoaDon getHDByID(String id) {
        String sql = "SELECT * FROM HOADON WHERE IDHD=?";
        ResultSet rs = JDBC_Helper.excuteQuery(sql, id);
        try {
            while (rs.next()) {

                KhachHang kh = khr.getKHByID(rs.getString(2));
                NhanVien nv = nvr.getNVByID(rs.getString(3));
                Coupon cp = cr.getCPByID(rs.getString(4));
                return new HoaDon(rs.getString(1), kh, nv, cp, rs.getString(5), rs.getFloat(6), rs.getInt(7), rs.getDate(8), rs.getInt(9),
                        rs.getDate(10), rs.getDate(11), rs.getString(12));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienResponsitory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public HoaDon getHDByMaHD(String ma) {
        String sql = "SELECT * FROM HOADON WHERE MAHD=?";
        ResultSet rs = JDBC_Helper.excuteQuery(sql, ma);
        try {
            while (rs.next()) {

                KhachHang kh = khr.getKHByID(rs.getString(2));
                NhanVien nv = nvr.getNVByID(rs.getString(3));
                Coupon cp = cr.getCPByID(rs.getString(4));
                return new HoaDon(rs.getString(1), kh, nv, cp, rs.getString(5), rs.getFloat(6), rs.getInt(7), rs.getDate(8), rs.getInt(9),
                        rs.getDate(10), rs.getDate(11), rs.getString(12));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienResponsitory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public HoaDon insertHD(HoaDon nv) {
        String sql = "INSERT dbo.HOADON(IDHD,IDKH,IDNV,MAHD,THANHTIEN,HINHTHUCTT,NGAYTT,TRANGTHAI,NGAYTAO,NGAYSUA)"
                + "VALUES(NEWID(),?,?,?,?,?,?,?, GETDATE(),NULL)";
        JDBC_Helper.excuteUpdate(sql, nv.getKh().getId(), nv.getNv().getId(), nv.getMa(), nv.getThanhTien(),
                nv.getHinhThucThanhToan(), nv.getNgayThanhToan(), nv.getTrangThai());
        return nv;
    }

    public HoaDon upadteHD(HoaDon nv) {
        String sql = "UPDATE dbo.HOADON SET IDKH =?, NGAYSUA=GETDATE() WHERE MAHD=?";
        JDBC_Helper.excuteUpdate(sql, nv.getKh().getId(), nv.getMa());
        return nv;
    }

    public HoaDon updateTinhTrangHD(HoaDon nv) {
        String sql = "UPDATE HOADON SET TINHTRANG = ? WHERE ID = ?";
        JDBC_Helper.excuteUpdate(sql, nv.getTrangThai(), nv.getId());
        return nv;
    }

    public HoaDon updateCopounHD(HoaDon nv) {
        String sql = "UPDATE HOADON SET IDCP = ? WHERE ID = ?";
        JDBC_Helper.excuteUpdate(sql, nv.getTrangThai(), nv.getId());
        return nv;
    }

    public HoaDon updateHuyHD(HoaDon nv) {
        String sql = "UPDATE dbo.HoaDon SET TrangThai = 2 ,NGAYSUA=GETDATE(), GhiChu=? WHERE MaHD=?";
        JDBC_Helper.excuteUpdate(sql, nv.getGhiChu(), nv.getMa());
        return nv;
    }

    public HoaDon upadteHD_ThanhToan(HoaDon nv) {
        String sql = "UPDATE HOADON SET IDCP = ? ,THANHTIEN=?,HINHTHUCTT=? , TRANGTHAI = ?, NGAYTT=GETDATE() WHERE MAHD=?";
        JDBC_Helper.excuteUpdate(sql, nv.getCp().getId(), nv.getThanhTien(), nv.getHinhThucThanhToan(), nv.getTrangThai(), nv.getMa());
        return nv;
    }

//
}
