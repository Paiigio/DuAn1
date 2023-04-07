
package responsitory;


import DomainModels.NSX;
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
public class NSXResponsitory {
        public ArrayList<NSX> getAllNSX() {
        ArrayList<NSX> list = new ArrayList<>();
        String sql="SELECT* FROM dbo.NSX";
        ResultSet rs = JDBC_Helper.excuteQuery(sql);
        try {
            while (rs.next()) {
                list.add(new NSX(rs.getString(1), rs.getString(2), rs.getString(3),rs.getDate(4), rs.getDate(5)));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(NSXResponsitory.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return list;
    }
       public NSX getNSXByID(String id){
        
        String sql="SELECT * FROM NSX WHERE IDNSX=?";
        ResultSet rs=JDBC_Helper.excuteQuery(sql,id);
        try {
            while(rs.next()){
                return new NSX(rs.getString(1), rs.getString(2), rs.getString(3),rs.getDate(4), rs.getDate(5));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NSXResponsitory.class.getName()).log(Level.SEVERE, null, ex);
        }
       return null;
    }
    public NSX insertNSX(NSX n){
        String sql= "INSERT INTO NSX VALUES(NEWID(),?,?,GETDATE(),null)";
       JDBC_Helper.excuteUpdate(sql, n.getMa(),n.getTen());
        return n;
    }
    public NSX updateNSX(NSX n){
        String sql= "UPDATE dbo.NSX SET MANSX=?,TENNSX=?,NGAYSUA=GETDATE() WHERE IDNSX=?";
       JDBC_Helper.excuteUpdate(sql,n.getMa(), n.getTen(),n.getId());
        return n;
    }
    public Integer deleteNSX(String ma){
        String sql="DELETE dbo.ChucVu WHERE MANSX =?";
        int row=JDBC_Helper.excuteUpdate(sql,ma);
        return row;
    }
  
}
