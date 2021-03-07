package org.example.utils;

import java.sql.*;

public class JDBCUtils {
    //创建一些静态成员变量，用来存储数据库的连接信息
    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:MySQL://localhost:3306/demo?rewriteBatchedStatements=true";
    private static String user = "root";
    private static String password = "root";

    //静态代码块，优先执行，里面的代码会被执行一次，多次调用此工具类时，注册驱动只需要执行一次
    static {
        //1.注册驱动
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //2.获取数据库连接对象
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException("数据库连接异常" + e);
        }
        return conn;
    }

    //3.释放资源
    public static void close(Connection conn, Statement stat, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
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
        if (stat != null) {
            try {
                stat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}

