package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {

    private static Connection conn = null;

    public static Connection getConnection() {
        if (conn == null) {
            try {
                Properties pros = loadProperties();
                String url = pros.getProperty("dburl");
                conn = DriverManager.getConnection(url, pros);

            } catch (SQLException e) {
                throw new DBException(e.getMessage());
            }
        }
        return conn;
    }

    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new DBException(e.getMessage());
            }
        }
    }

    private static Properties loadProperties() {
        try (FileInputStream fis = new FileInputStream("db.properties")) {
            Properties prop = new Properties();
            prop.load(fis);
            return prop;
        } catch (IOException e) {
            throw new DBException(e.getMessage());
        }

    }

    public static void closeStatement(Statement stt) {
        if (stt != null) {
            try {
                stt.close();
            } catch (SQLException e) {
                throw new DBException(e.getMessage());
            }
        }
    }

    public static void closeResultSet(ResultSet rst) {
        if (rst != null) {
            try {
                rst.close();
            } catch (SQLException e) {
                throw new DBException(e.getMessage());
            }
        }
    }

}
