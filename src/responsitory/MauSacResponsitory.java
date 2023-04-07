
package responsitory;

import DomainModels.ChucVu;
import DomainModels.MauSac;
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
public class MauSacResponsitory {
          public ArrayList<MauSac> getAllMauSac() {
        ArrayList<MauSac> list = new ArrayList<>();
        String sql="SELECT* FROM dbo.MauSac";
        ResultSet rs = JDBC_Helper.excuteQuery(sql);
        try {
            while (rs.next()) {
                list.add(new MauSac(rs.getString(1),rs.getString(2),rs.getString(3),rs.getDate(4),rs.getDate(5)));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(MauSacResponsitory.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return list;
    }
       public MauSac getMSByID(String id){
        
        String sql="SELECT * FROM MauSac WHERE IDMS=?";
        ResultSet rs=JDBC_Helper.excuteQuery(sql,id);
        try {
            while(rs.next()){
                return new MauSac(rs.getString(1),rs.getString(2),rs.getString(3),rs.getDate(4),rs.getDate(5));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ChucVuResponsitory.class.getName()).log(Level.SEVERE, null, ex);
        }
       return null;
    }
    public MauSac insertMS(MauSac cv){
        String sql= "INSERT INTO MauSac VALUES(NEWID(),?,?,GETDATE(),GETDATE())";
       JDBC_Helper.excuteUpdate(sql, cv.getMa(),cv.getTen());
        return cv;
    }
    public MauSac updateMS(MauSac cv){
        String sql= "UPDATE dbo.MauSac SET MAMAU=?, TenmAU=?,NGAYSUA=GETDATE() WHERE idms=?";
       JDBC_Helper.excuteUpdate(sql,cv.getMa(), cv.getTen(),cv.getId());
        return cv;
    }
    public Integer deleteMS(String ma){
        String sql="DELETE dbo.MauSac WHERE ma =?";
        int row=JDBC_Helper.excuteUpdate(sql,ma);
        return row;
    }
}
