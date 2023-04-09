
package responsitory;

import DomainModels.NhaPhanPhoi;
import Utilites.JDBC_Helper;
import java.util.ArrayList;
import  java.sql.*;
/**
 *
 * @author duong
 */
public class NhaPhanPhoiResponsitory {
         public ArrayList<NhaPhanPhoi> getAllNPP() {
        ArrayList<NhaPhanPhoi> list = new ArrayList<>();
        String sql="SELECT* FROM dbo.NHAPHANPHOI";
        ResultSet rs = JDBC_Helper.excuteQuery(sql);
        try {
            while (rs.next()) {
                list.add(new NhaPhanPhoi(rs.getString(1), rs.getString(2),rs.getString(3), rs.getString(4), rs.getString(5)
                        , rs.getString(6), rs.getDate(7),rs.getDate(8)));
                
            }
        } catch (SQLException ex) {
          
            ex.printStackTrace();
        }
        return list;
    }
       public NhaPhanPhoi getNPPByID(String id){
        
        String sql="SELECT * FROM NHAPHANPHOI WHERE IDNPP=?";
        ResultSet rs=JDBC_Helper.excuteQuery(sql,id);
        try {
            while(rs.next()){
                return new NhaPhanPhoi(rs.getString(1), rs.getString(2),rs.getString(3), rs.getString(4), rs.getString(5)
                        , rs.getString(6), rs.getDate(7),rs.getDate(8));
            }
        } catch (SQLException ex) {
          
        }
       return null;
    }
    public NhaPhanPhoi insertNPP(NhaPhanPhoi cv){
        String sql= "INSERT INTO dbo.NHAPHANPHOI VALUES(NEWID(),?,?,?,?,?,GETDATE(),NULL)";
       JDBC_Helper.excuteUpdate(sql, cv.getMa(),cv.getTen(),cv.getDiaChi(),cv.getEmail(),cv.getSdt());
        return cv;
    }
    public NhaPhanPhoi updateNPP(NhaPhanPhoi cv){
        String sql= "UPDATE dbo.NHAPHANPHOI SET TENNHAPHANPHOI =?, DIACHI=?,EMAIL=?,SDT=?,NGAYSUA=GETDATE() WHERE MANHAPHANPHOI=?";
       JDBC_Helper.excuteUpdate(sql, cv.getTen(),cv.getDiaChi(),cv.getEmail(),cv.getSdt(),cv.getMa());
        return cv;
    }
}
