/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsitory;

import DomainModels.ChucVu;
import DomainModels.NhanVien;
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
public class NhanVienResponsitory {

    ChucVuResponsitory cvr = new ChucVuResponsitory();

    public ArrayList<NhanVien> getAllNhanVien() {
        ArrayList<NhanVien> list = new ArrayList<>();
        String sql = "SELECT * FROM dbo.NHANVIEN ";
        ResultSet rs = JDBC_Helper.excuteQuery(sql);

        try {
            while (rs.next()) {
                ChucVu cv = cvr.getCVByID(rs.getString(2));
                list.add(new NhanVien(rs.getString(1), cv, rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getDate(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getInt(11),
                        rs.getString(12), rs.getDate(13), rs.getDate(14)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienResponsitory.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public ArrayList<NhanVien> getTim(String ma) {
        ArrayList<NhanVien> list = new ArrayList<>();
        String sql = "SELECT Manv,hoTen FROM dbo.NhanVien WHERE Ma=?";
        ResultSet rs = JDBC_Helper.excuteQuery(sql);

        try {
            while (rs.next()) {
                list.add(new NhanVien(rs.getString(1), rs.getString(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienResponsitory.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public ArrayList<NhanVien> getTimTen(String ten) {
        ArrayList<NhanVien> list = new ArrayList<>();
        String sql = "SELECT * FROM dbo.NhanVien where hoten like ? ";
        ResultSet rs = JDBC_Helper.excuteQuery(sql, "%" + ten + "%");
        try {
            while (rs.next()) {
                ChucVu cv = cvr.getCVByID(rs.getString(2));
                list.add(new NhanVien(rs.getString(1), cv, rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getDate(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getInt(11),
                        rs.getString(12), rs.getDate(13), rs.getDate(14)));

            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienResponsitory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<NhanVien> getVaiTro(String tencv) {
        ArrayList<NhanVien> list = new ArrayList<>();
        String sql = "Select nhanvien.* from nhanvien join chucvu ON CHUCVU.IDCV = NHANVIEN.IDCV\n" +
                            "WHERE TENCV =?";
        ResultSet rs = JDBC_Helper.excuteQuery(sql, tencv);
        try {
            while (rs.next()) {
                ChucVu cv = cvr.getCVByID(rs.getString(2));
                list.add(new NhanVien(rs.getString(1), cv,rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getDate(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getInt(11),
                        rs.getString(12), rs.getDate(13), rs.getDate(14)));

            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienResponsitory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
       public ArrayList<NhanVien> getTT(String tt) {
        ArrayList<NhanVien> list = new ArrayList<>();
        String sql = "Select nhanvien.* from nhanvien join chucvu ON CHUCVU.IDCV = NHANVIEN.IDCV\n" +
"WHERE TRANGTHAI=?";
        ResultSet rs = JDBC_Helper.excuteQuery(sql, tt);
        try {
            while (rs.next()) {
                ChucVu cv = cvr.getCVByID(rs.getString(2));
                list.add(new NhanVien(rs.getString(1), cv,rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getDate(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getInt(11),
                        rs.getString(12), rs.getDate(13), rs.getDate(14)));

            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienResponsitory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public NhanVien getNVByID(String id) {
        String sql = "SELECT Manv,hoTEN FROM NhanVien WHERE idnv=?";
        ResultSet rs = JDBC_Helper.excuteQuery(sql, id);
        try {
            while (rs.next()) {
                return new NhanVien(rs.getString(1), rs.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienResponsitory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public NhanVien insertNhanVien(NhanVien nv) {

        String sql = "INSERT INTO NHANVIEN VALUES (NEWID(),?,?,?,?,?,?,?,?,?,?,?,GETDATE(),null)";
        JDBC_Helper.excuteUpdate(sql, nv.getCv().getId(), nv.getMa(), nv.getHoTen(), nv.getGioiTinh(), nv.getSdt(), nv.getNgaySinh(),
                nv.getDiaChi(), nv.getEmail(), nv.getMatKhau(), nv.getTrangThai(), nv.getHinhAnh());
        return nv;
    }

    public NhanVien updateNV(NhanVien nv) {
        String sql = "UPDATE dbo.NHANVIEN SET IDCV=?, HOTEN=?,GIOTINH=?,SDT=?,NGAYSINH=?,DIACHI=?,EMAIL=?,MATKHAU=?,TRANGTHAI=?,"
                + "HINHANH=?,NGAYSUA=GETDATE() WHERE manv =?";
        JDBC_Helper.excuteUpdate(sql, nv.getCv().getId(), nv.getHoTen(), nv.getGioiTinh(), nv.getSdt(), nv.getNgaySinh(),
                nv.getDiaChi(), nv.getEmail(), nv.getMatKhau(), nv.getTrangThai(), nv.getHinhAnh(), nv.getMa());
        return nv;
    }

    public Integer deleteNV(String id) {
        String sql = "DELETE FROM NHANVIEN WHERE ID=?";
        int row = JDBC_Helper.excuteUpdate(sql, id);
        return row;
    }

}
