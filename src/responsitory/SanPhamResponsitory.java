
package responsitory;

import DomainModels.NSX;
import DomainModels.SanPham;
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
public class SanPhamResponsitory {
        NSXResponsitory nsx = new NSXResponsitory();

        public ArrayList<SanPham> getAllSanPham() {
        ArrayList<SanPham> list = new ArrayList<>();
        String sql="SELECT* FROM dbo.SanPham";
        ResultSet rs = JDBC_Helper.excuteQuery(sql);
        try {
            while (rs.next()) {
                NSX n = nsx.getNSXByID(rs.getString(2));
                list.add(new SanPham(rs.getString(1),n , rs.getString(3), rs.getString(4), rs.getString(5), rs.getDate(6), rs.getDate(7)));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamResponsitory.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return list;
    }
       public SanPham getSPByID(String id){
        
        String sql="SELECT * FROM SANPHAM WHERE IDSP=?";
        ResultSet rs=JDBC_Helper.excuteQuery(sql,id);
        try {
            while(rs.next()){
                NSX n = nsx.getNSXByID(rs.getString(2));                
                return new SanPham(rs.getString(1),n , rs.getString(3), rs.getString(4), rs.getString(5), rs.getDate(6), rs.getDate(7));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamResponsitory.class.getName()).log(Level.SEVERE, null, ex);
        }
       return null;
    }
    public SanPham insertSP(SanPham sp){
        String sql= "INSERT INTO SANPHAM VALUES(NEWID(),?,?,?,?,GETDATE(),null)";
       JDBC_Helper.excuteUpdate(sql, sp.getNsx().getId(),sp.getMa(),sp.getTen(),sp.getMoTa());
        return sp;
    }
    public SanPham updateSP(SanPham sp){
        String sql= "UPDATE dbo.SANPHAM SET IDNSX=?,MASP=?,TENSP=?,MOTA=?,NGAYSUA=GETDATE() WHERE IDSP=?";
       JDBC_Helper.excuteUpdate(sql, sp.getNsx().getId(),sp.getMa(),sp.getTen(),sp.getMoTa(),sp.getId());
        return sp;
    }
    public Integer deleteSP(String ma){
        String sql="DELETE dbo.SANPHAM WHERE maSP =?";
        int row=JDBC_Helper.excuteUpdate(sql,ma);
        return row;
    }
  
}
