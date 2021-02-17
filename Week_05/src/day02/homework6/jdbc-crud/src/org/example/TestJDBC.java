package org.example;

import org.example.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestJDBC {
    public static void main(String[] args) throws SQLException {
//        select();
//        selectRowById(1);
        delete();
//        update();
//        insert();
    }

    private static void update() {
        Connection connection = JDBCUtils.getConnection();
        Statement statement = null;

        try {
            statement = connection.createStatement();
            int row = statement.executeUpdate("update t_user set password = '888' where id = '3'");
            if (row>0){
                System.out.println("更新成功");
            }else {
                System.out.println("数据库无该条数据");
            }
        } catch (SQLException throwables) {
            System.out.println("更新失败");
            throwables.printStackTrace();
        }finally {
            JDBCUtils.close(connection,statement,null);
        }
    }

    private static void delete() {
        Connection connection = JDBCUtils.getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            int row = statement.executeUpdate("delete from t_user ");
            if (row>0){
                System.out.println("删除成功");
            }else {
                System.out.println("没有这条数据");
            }
        } catch (SQLException throwables) {
            System.out.println("删除失败");
            throwables.printStackTrace();
        }finally {
            JDBCUtils.close(connection,statement,null);
        }
    }


    private static void selectRowById(int id) {
        Connection connection = JDBCUtils.getConnection();
        Statement statement = null;
        ResultSet rs = null;
        try {
             statement = connection.createStatement();
             rs = statement.executeQuery("select * from t_user where id = "+id);
            if (rs.next()){
                String username = rs.getString("username");
                String password = rs.getString("password");
                String number = rs.getString("number");
                System.out.println(id);
                System.out.println(username);
                System.out.println(password);
                System.out.println(number);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtils.close(connection,statement,rs);
        }

    }

    private static void select() {
        Connection connection = JDBCUtils.getConnection();
        Statement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery("select * from t_user");
            while (rs.next()){
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String number = rs.getString("number");
                System.out.println(id);
                System.out.println(username);
                System.out.println(password);
                System.out.println(number);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtils.close(connection,statement,rs);
        }
    }

    private static void insert() {
        Connection connection = JDBCUtils.getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            int row = statement.executeUpdate("insert into t_user (id,username,password,number) " +
                    "values ('12','李华','123456778','123')");
            if (row>0){
                System.out.println("插入成功");
            }
        } catch (SQLException throwables) {
            System.out.println("插入失败");
            throwables.printStackTrace();
        } finally {
            JDBCUtils.close(connection,statement,null);
        }
    }
}
