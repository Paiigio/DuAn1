
package responsitory;

import DomainModels.NhaPhanPhoi;
import DomainModels.NhanVien;
import DomainModels.PhieuChi;
import DomainModels.PhieuNhap;
import Utilites.JDBC_Helper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author duong
 */
public class PhieuChiResponsitory {
    NhanVienResponsitory nvr = new NhanVienResponsitory();
    PhieuNhapResponsitory pnr= new PhieuNhapResponsitory();
    public ArrayList<PhieuChi> getAllPhieuChi() {
        ArrayList<PhieuChi> list = new ArrayList<>();
        String sql = "SELECT * FROM dbo.PHIEUCHI ";
        ResultSet rs = JDBC_Helper.excuteQuery(sql);

        try {
            while (rs.next()) {
                PhieuNhap pn = pnr.getPNByID(rs.getString(1));
                NhanVien nv = nvr.getNVByID(rs.getString(2));

                list.add(new PhieuChi(rs.getString(1), pn, nv, rs.getString(3), rs.getFloat(4), rs.getDate(5), rs.getDate(6), rs.getDate(7)));
            }
        } catch (SQLException ex) {

            ex.printStackTrace();
        }

        return list;
    }
      public PhieuChi getPCByID(String id) {

        String sql = "SELECT * FROM PHIEUCHI WHERE IDNPC=?";
        ResultSet rs = JDBC_Helper.excuteQuery(sql, id);
        try {
            while (rs.next()) {
                PhieuNhap pn = pnr.getPNByID(rs.getString(1));
                NhanVien nv = nvr.getNVByID(rs.getString(2));

               return new PhieuChi(rs.getString(1), pn, nv, rs.getString(3), rs.getFloat(4), rs.getDate(5), rs.getDate(6), rs.getDate(7));
            }
        } catch (SQLException ex) {

        }
        return null;
    }
//  public PhieuChi insertPC(PhieuChi cv) {
//        String sql = "INSERT INTO dbo.PHIEUNHAP(ID,IDNPP,IDNV,MAPN,TONGGIA,NGAYTAO) VALUES(NEWID(),?,\n" +
//"?,?,?,GETDATE())";
//        JDBC_Helper.excuteUpdate(sql, cv.getNhaPhanPhoi().getId(), cv.getNhanVien().getId(), cv.getMaPN(), cv.getTongGia());
//        return cv;
//    }
//
//    public PhieuNhap updatePN(PhieuNhap cv) {
//        String sql = "UPDATE dbo.PHIEUNHAP SET IDNPP=?, IDNV=?, TONGGIA=? WHERE MAPN=?";
//        JDBC_Helper.excuteUpdate(sql, cv.getNhaPhanPhoi().getId(), cv.getNhanVien().getId(), cv.getTongGia(), cv.getMaPN());
//        return cv;
//    }
}
