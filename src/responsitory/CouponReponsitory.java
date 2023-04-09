
package responsitory;
import DomainModels.Coupon;
import Utilites.JDBC_Helper;
import java.sql.*;

/**
 *
 * @author WellCome Win1021H2
 */
import java.util.ArrayList;
public class CouponReponsitory {
        public ArrayList<Coupon> getAllCoupon() {
        ArrayList<Coupon> list = new ArrayList<>();
        String sql="SELECT* FROM dbo.Coupon";
        ResultSet rs = JDBC_Helper.excuteQuery(sql);
        try {
            while (rs.next()) {
                list.add(new Coupon(rs.getString(1),rs.getString(2),rs.getDate(3),rs.getString(4),rs.getFloat(5),rs.getDate(6),rs.getDate(7)));
                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
       public Coupon getCPByID(String id){
        
        String sql="SELECT * FROM COUPON WHERE IDCP=?";
        ResultSet rs=JDBC_Helper.excuteQuery(sql,id);
        try {
            while(rs.next()){
                return new Coupon(rs.getString(1),rs.getString(2),rs.getDate(3),rs.getString(4),rs.getFloat(5),rs.getDate(6),rs.getDate(7));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       return null;
    }
       public Coupon getCPByMa(String ma){
        
        String sql="SELECT * FROM COUPON WHERE MACOUPON=?";
        ResultSet rs=JDBC_Helper.excuteQuery(sql,ma);
        try {
            while(rs.next()){
                return new Coupon(rs.getString(1),rs.getString(2),rs.getDate(3),rs.getString(4),rs.getFloat(5),rs.getDate(6),rs.getDate(7));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       return null;
    }
    public Coupon insertCP(Coupon cp){
        String sql= "INSERT INTO COUPON VALUES(NEWID(),?,?,?,?,GETDATE(),null)";
       JDBC_Helper.excuteUpdate(sql, cp.getMa(),cp.getHanSuDung(),cp.getHinhThuc(),cp.getGiamGia());
        return cp;
    }
    public Coupon updateCP(Coupon cp){
        String sql= "UPDATE dbo.COUPON SET MACOUPON=?,HANSUDUNG=?,HINHTHUC=?,GIAMGIA=?,NGAYSUA=GETDATE() WHERE IDCP=?";
       JDBC_Helper.excuteUpdate(sql, cp.getMa(),cp.getHanSuDung(),cp.getHinhThuc(),cp.getGiamGia(),cp.getId());
        return cp;
    }
    public Integer deleteCP(String id){
        String sql="DELETE dbo.Coupon WHERE idCP =?";
        int row=JDBC_Helper.excuteUpdate(sql,id);
        return row;
    }
}
