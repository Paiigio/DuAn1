package responsitory;

import DomainModels.DungLuong;
import Utilites.JDBC_Helper;
import java.util.ArrayList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kazee
 */
public class DungLuongResponsitory {

    public ArrayList<DungLuong> getAllDungLuong() {
        ArrayList<DungLuong> list = new ArrayList<>();
        String sql = "SELECT* FROM dbo.DungLuong";
        ResultSet rs = JDBC_Helper.excuteQuery(sql);
        try {
            while (rs.next()) {
                list.add(new DungLuong(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4)));
            }
        } catch (Exception ex) {
            Logger.getLogger(DungLuongResponsitory.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return list;
    }

    public DungLuong getDLByID(String id) {

        String sql = "SELECT * FROM DungLuong WHERE IDDL=?";
        ResultSet rs = JDBC_Helper.excuteQuery(sql, id);
        try {
            while (rs.next()) {
                return new DungLuong(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DungLuongResponsitory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public DungLuong insertDL(DungLuong dl) {
        String sql = "INSERT INTO DungLuong VALUES(NEWID(),?,?,GETDATE())";
        JDBC_Helper.excuteUpdate(sql,dl.getMa(),dl.getSoDungLuong());
        return dl;
    }

    public DungLuong updateDL(DungLuong cv) {
        String sql = "UPDATE DungLuong SET MADL=?,SOLUONG=?,NGAYNHAP=GETDATE() WHERE idDL=?";
        JDBC_Helper.excuteUpdate(sql, cv.getMa(), cv.getSoDungLuong(), cv.getId());
        return cv;
    }

    public Integer deleteDL(String ma) {
        String sql = "DELETE DUNGLUONG WHERE maDL =?";
        int row = JDBC_Helper.excuteUpdate(sql, ma);
        return row;
    }
}
