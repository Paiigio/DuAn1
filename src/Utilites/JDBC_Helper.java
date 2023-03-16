
package Utilites;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;



public class JDBC_Helper {
    public  static  ResultSet excuteQuery(String sql,Object ...args){
        Connection cn=null;
        PreparedStatement ps = null;
        ResultSet rs=null;
        
        try {
            cn=DB_Context.getConnection();
            ps=cn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1,args[i]);
                
            }
            rs=ps.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;    
    } 
        public  static  int excuteUpdate(String sql,Object ...args){
        Connection cn=null;
        PreparedStatement ps = null;
        int row=0;
        
        try {
            cn=DB_Context.getConnection();
            ps=cn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1,args[i]);
                
            }
            row=ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;    
    } 

}
