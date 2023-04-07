
package responsitory;

import DomainModels.ChucVu;
import DomainModels.KhachHang;
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
public class KhachHangResponsitory {

    public ArrayList<KhachHang> getAllKH() {
        ArrayList<KhachHang> list = new ArrayList<>();
        String sql = "SELECT IDKH,MAKH,HOTEN,SDT,DIACHI,GIOITINH,EMAIL,NGAYSINH,NGAYTAO,NGAYSUA FROM dbo.KHACHHANG ";
        ResultSet rs = JDBC_Helper.excuteQuery(sql);

        try {
            while (rs.next()) {

                list.add(new KhachHang(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getDate(8),
                        rs.getDate(9), rs.getDate(10)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienResponsitory.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public ArrayList<KhachHang> getTim(String ma) {
        ArrayList<KhachHang> list = new ArrayList<>();
        String sql = "SELECT IDKH,MAKH,HOTEN,SDT,DIACHI,GIOITINH,EMAIL,NGAYSINH,NGAYTAO,NGAYSUA FROM dbo.KHACHHANG  WHERE Makh = ?";
        ResultSet rs = JDBC_Helper.excuteQuery(sql, ma);

        try {
            while (rs.next()) {
                list.add(new KhachHang(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getDate(8),
                        rs.getDate(9), rs.getDate(10)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienResponsitory.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public KhachHang getTimKH(String sdt) {

        String sql = "SELECT IDKH,MAKH,HOTEN,SDT,DIACHI,GIOITINH,EMAIL,NGAYSINH,NGAYTAO,NGAYSUA FROM dbo.KHACHHANG  WHERE SDT like ? ";
        ResultSet rs = JDBC_Helper.excuteQuery(sql, "%" + sdt + "%");
        try {
            while (rs.next()) {
                return new KhachHang(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getDate(8),
                        rs.getDate(9), rs.getDate(10));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
       public ArrayList<KhachHang> getTimSDT(String sdt) {
        ArrayList<KhachHang> list = new ArrayList<>();
        String sql = "SELECT IDKH,MAKH,HOTEN,SDT,DIACHI,GIOITINH,EMAIL,NGAYSINH,NGAYTAO,NGAYSUA FROM dbo.KHACHHANG  WHERE SDT like ? ";
        ResultSet rs = JDBC_Helper.excuteQuery(sql, "%" + sdt + "%");
        try {
            while (rs.next()) {
                list.add(new KhachHang(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getDate(8),
                        rs.getDate(9), rs.getDate(10)));

            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienResponsitory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public KhachHang insertKH(KhachHang nv) {
        String sql = "INSERT INTO dbo.KHACHHANG(IDKH, MAKH, SDT,HOTEN, DIACHI, GIOITINH,"
                + "EMAIL,NGAYSINH, NGAYTAO,NGAYSUA)VALUES(NEWID(),?,?,?,?,?,?,?,GETDATE(),NULL)";
        JDBC_Helper.excuteUpdate(sql, nv.getMaKH(), nv.getHoTen(), nv.getSdt(), nv.getDiaChi(), nv.getGioiTinh(),
                nv.getEmail(), nv.getNgaySinh());
        return nv;
    }

    public KhachHang updateKH(KhachHang nv) {
        String sql = "UPDATE dbo.KHACHHANG SET HOTEN=?,SDT=?,DIACHI=?,GIOITINH=?,EMAIL=?,NGAYSINH=?,NGAYSUA=GETDATE() WHERE MAKH=?";
        JDBC_Helper.excuteUpdate(sql, nv.getSdt(), nv.getHoTen(), nv.getDiaChi(), nv.getGioiTinh(),
                nv.getEmail(), nv.getNgaySinh(), nv.getMaKH());
        return nv;
    }
    public KhachHang getKHByID(String id) {
        String sql = "SELECT IDKH,MAKH,HOTEN,SDT,DIACHI,GIOITINH,EMAIL,NGAYSINH,NGAYTAO,NGAYSUA FROM dbo.KHACHHANG WHERE IDKH=?";
        ResultSet rs = JDBC_Helper.excuteQuery(sql, id);
        try {
            while (rs.next()) {
           
               return new KhachHang(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getDate(8),
                        rs.getDate(9), rs.getDate(10));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienResponsitory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
