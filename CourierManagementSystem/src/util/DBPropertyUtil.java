package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBPropertyUtil {

    private static final String fileName = "db.properties";

    public static Connection getDbConnection() {
        Connection con = null;
        try {
            String connStr = DBConnUtil.getConnectionString(fileName);
            con = DriverManager.getConnection(connStr);
        } catch (Exception e) {
            System.out.println("Error establishing DB connection.");
            e.printStackTrace();
        }
        return con;
    }
}