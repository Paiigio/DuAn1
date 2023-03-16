
package Utilites;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DB_Context {
    public static String URL = "jdbc:sqlserver://localhost:1433;databaseName=DUAN1_NHOM8;encrypt=true;trustServerCertificate=true;";
    public static String USERNAME = "sa";
    public static String PASS = "123";

    static {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DB_Context.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Connection getConnection() {
        Connection cn = null;
        try {
            cn = DriverManager.getConnection(URL, USERNAME, PASS);
        } catch (SQLException ex) {
            Logger.getLogger(DB_Context.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cn;
    }

    public static void main(String[] args) {
        Connection cn = getConnection();
        if (cn != null) {
            System.out.println("Done!!");
        } else {
            System.out.println("Error !!");
        }
    }
}
