
package responsitory;

import DomainModels.NhaPhanPhoi;
import DomainModels.NhanVien;
import DomainModels.PhieuNhap;
import Utilites.JDBC_Helper;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author duong
 */
public class PhieuNhapResponsitory {

    NhaPhanPhoiResponsitory nppr = new NhaPhanPhoiResponsitory();
    NhanVienResponsitory nvr = new NhanVienResponsitory();

    public ArrayList<PhieuNhap> getAllPhieuNhap() {
        ArrayList<PhieuNhap> list = new ArrayList<>();
        String sql = "SELECT * FROM dbo.PHIEUNHAP ";
        ResultSet rs = JDBC_Helper.excuteQuery(sql);

        try {
            while (rs.next()) {
                NhaPhanPhoi npp = nppr.getNPPByID(rs.getString(2));
                NhanVien nv = nvr.getNVByID(rs.getString(3));

                list.add(new PhieuNhap(rs.getString(1), npp, nv, rs.getString(4), rs.getFloat(5), rs.getDate(6)));
            }
        } catch (SQLException ex) {

            ex.printStackTrace();
        }

        return list;
    }

    public PhieuNhap getPNByID(String id) {

        String sql = "SELECT * FROM PHIEUNHAP WHERE IDPN=?";
        ResultSet rs = JDBC_Helper.excuteQuery(sql, id);
        try {
            while (rs.next()) {
                NhaPhanPhoi npp = nppr.getNPPByID(rs.getString(2));
                NhanVien nv = nvr.getNVByID(rs.getString(3));
                return new PhieuNhap(rs.getString(1), npp, nv, rs.getString(4), rs.getFloat(5), rs.getDate(6));
            }
        } catch (SQLException ex) {

        }
        return null;
    }

    public PhieuNhap insertPN(PhieuNhap cv) {
        String sql = "INSERT INTO dbo.PHIEUNHAP(ID,IDNPP,IDNV,MAPN,TONGGIA,NGAYTAO) VALUES(NEWID(),?,\n" +
"?,?,?,GETDATE())";
        JDBC_Helper.excuteUpdate(sql, cv.getNhaPhanPhoi().getId(), cv.getNhanVien().getId(), cv.getMaPN(), cv.getTongGia());
        return cv;
    }

    public PhieuNhap updatePN(PhieuNhap cv) {
        String sql = "UPDATE dbo.PHIEUNHAP SET IDNPP=?, IDNV=?, TONGGIA=? WHERE MAPN=?";
        JDBC_Helper.excuteUpdate(sql, cv.getNhaPhanPhoi().getId(), cv.getNhanVien().getId(), cv.getTongGia(), cv.getMaPN());
        return cv;
    }
}
