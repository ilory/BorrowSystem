package Utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtils {
    static String driverClass = null;
    static String url = null;
    static String name = null;
    static String password =null;
    static {
        try{
            Properties properties =new Properties();
            InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            properties.load(is);
            driverClass = properties.getProperty("driverClass");
            url = properties.getProperty("url");
            name = properties.getProperty("name");
            password = properties.getProperty("password");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    /*
    获取连接
     */
    public static Connection getConnection() throws Exception {
        Class.forName(driverClass);
        Connection con = DriverManager.getConnection(url,name,password);
        return con;
    }

    /*
    关闭Statement 和 Connection
     */
    public static void release(Statement sta, Connection con) {
        if (sta != null) {
            try {
                sta.close();
            } catch (Exception e1) {
                e1.getStackTrace();
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (Exception e2) {
                e2.getStackTrace();
            }
        }
    }

    /*
    关闭Statement 和 Connection 和 ResultSet
     */
    public static void release(Statement sta, Connection con, ResultSet res) {
        if (sta != null) {
            try {
                sta.close();
            } catch (Exception e1) {
                e1.getStackTrace();
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (Exception e2) {
                e2.getStackTrace();
            }
        }
        if (res != null) {
            try {
                res.close();
            } catch (Exception e3) {
                e3.getStackTrace();
            }
        }
    }

}
