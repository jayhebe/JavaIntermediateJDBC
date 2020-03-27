package com.exercises.jdbc;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCTools {
    public static Connection getConnection() throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("jdbc.properties"));

        String driverClass = properties.getProperty("driver");
        String jdbcUrl = properties.getProperty("jdbcUrl");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");

        Class.forName(driverClass);

        return DriverManager.getConnection(jdbcUrl, user, password);
    }

    public static ResultSet query(String sql) {
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            stat = conn.createStatement();
            rs = stat.executeQuery(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }

    public static void update(String sql) {
        Connection conn = null;
        Statement stat = null;

        try {
            conn = getConnection();
            stat = conn.createStatement();
            stat.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            release(null, stat, conn);
        }
    }

    public static void release(ResultSet rs, Statement stat, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (stat != null) {
            try {
                stat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
