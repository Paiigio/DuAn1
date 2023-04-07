
package responsitory;

import DomainModels.ChucVu;
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
public class ChucVuResponsitory {
        public ArrayList<ChucVu> getAllChucVu() {
        ArrayList<ChucVu> list = new ArrayList<>();
        String sql="SELECT* FROM dbo.CHUCVU";
        ResultSet rs = JDBC_Helper.excuteQuery(sql);
        try {
            while (rs.next()) {
                list.add(new ChucVu(rs.getString(1),rs.getString(2),rs.getString(3),rs.getDate(4),rs.getDate(5)));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ChucVuResponsitory.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return list;
    }
       public ChucVu getCVByID(String id){
        
        String sql="SELECT * FROM CHUCVU WHERE IDCV=?";
        ResultSet rs=JDBC_Helper.excuteQuery(sql,id);
        try {
            while(rs.next()){
                return new ChucVu(rs.getString(1),rs.getString(2),rs.getString(3),rs.getDate(4),rs.getDate(5));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ChucVuResponsitory.class.getName()).log(Level.SEVERE, null, ex);
        }
       return null;
    }
    public ChucVu insertCV(ChucVu cv){
        String sql= "INSERT INTO CHUCVU VALUES(NEWID(),?,?,GETDATE(),GETDATE())";
       JDBC_Helper.excuteUpdate(sql, cv.getMa(),cv.getTenCV());
        return cv;
    }
    public ChucVu updateCV(ChucVu cv){
        String sql= "UPDATE dbo.ChucVu SET Tencv=?,NGAYSUA=GETDATE() WHERE MACV=?";
       JDBC_Helper.excuteUpdate(sql, cv.getTenCV(),cv.getMa());
        return cv;
    }
    public Integer deleteCV(String ma){
        String sql="DELETE dbo.ChucVu WHERE maCV =?";
        int row=JDBC_Helper.excuteUpdate(sql,ma);
        return row;
    }
  
}
