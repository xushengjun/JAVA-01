package org.example;

import org.example.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class TestPstat {
    public static void main(String[] args) {
        insertBatch();
    }

    private static void insertBatch() {
        long start = System.currentTimeMillis();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            connection.setAutoCommit(false);//一定要紧跟JDBCUtils.getConnection();获取连接之后，否则会报错
            preparedStatement = connection.prepareStatement(
                    "insert into t_user (id,username,password,number) " +
                    "values (?,?,?,?)");
            for (int i = 0; i < 10000; i++) {
                preparedStatement.setInt(1,i);
                preparedStatement.setString(2,"aa");
                preparedStatement.setString(3,"aaa");
                preparedStatement.setString(4,"aaaa");

                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            connection.commit();
            System.out.println("耗时："+(System.currentTimeMillis()-start));
        } catch (SQLException throwables) {
            System.out.println("插入失败");
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        } finally {
            JDBCUtils.close(connection,preparedStatement,null);
        }
    }
}
