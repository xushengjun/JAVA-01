package org.example;


import org.example.utils.JDBCUtils;

import java.sql.*;
import java.util.Arrays;

/*
    使用JDBC技术,实现对数据库表的增删改查
 */
public class DemoJDBC {
    public static void main(String[] args) throws SQLException {
        long[] time = new long[10];
        for (int i = 0; i < 10; ) {
            long start = System.currentTimeMillis();
            insertBatch();
            long end = System.currentTimeMillis();
            time[i] = end-start;
            System.out.println("第"+ ++i +"次批量插入");
            truncate();
        }

        double average = Arrays.stream(time).average().getAsDouble();
        System.out.println("平均耗时："+average);
        //update();
//        delete();
//        select();
//        select_row();
        truncate();
    }

    /*
     使用JDBC技术,实现对数据库表进行查询数据
     只查询一行数据
 */
    private static void select_row() throws SQLException {
        //1.使用JDBCUtils工具类,获取数据库连接对象Connection
        Connection conn = JDBCUtils.getConnection();
        //2.获取执行sql语句的执行者对象Statement
        Statement stat = conn.createStatement();
        //3.执行sql语句,获取结果
        ResultSet rs = stat.executeQuery("SELECT * FROM t_order limit 1");
        //4.处理结果
        if (rs.next()) {
            int cid = rs.getInt("id");
            String cname = rs.getString("order_no");
            System.out.println(cid + "\t" + cname);
        } else {
            System.out.println("没有查询到结果!");
        }
        //5.释放资源
        JDBCUtils.close(conn, stat, rs);
    }

    /*
       使用JDBC技术,实现对数据库表进行查询数据
    */
    private static void select() throws SQLException {
        //1.使用JDBCUtils,获取数据库连接对象Connection
        Connection conn = JDBCUtils.getConnection();
        //2.获取执行sql语句的执行者对象Statement
        Statement stat = conn.createStatement();
    /*
        3.执行sql语句,获取结果
        使用Statement接口中的方法
            ResultSet executeQuery(String sql) 执行给定的 SQL 语句，该语句可能为SELECT
            参数:
                String sql:查询的sql语句
            返回值:
                java.sql.ResultSet接口:返回的是ResultSet接口的实现类对象,我们无需关注返回的是ResultSet接口的哪个实现类对象
                我们只需要会使用ResultSet接口来接收这个实现类对象即可(多态)
     */
        ResultSet rs = stat.executeQuery("SELECT * FROM category;");
        System.out.println(rs);//com.mysql.jdbc.JDBC42ResultSet@27fa135a

    /*
        4.处理结果:遍历集合
            在ResultSet接口有一个方法next,用于判断还有没有下一行数据
                boolean next() 将光标从当前位置向前移一行。
                有返回true,没有返回false
            此方法相当于迭代器Iterator中hasNext方法
            我们发现判断有没有下一行数据是一个重复的过程,所以可以使用循环优化
            不知道有多少行数据,使用while循环,while循环结束条件,next方法返回false
     */
        while (rs.next()) {//相当于it.hasNext()
        /*
            如果有数据,那么我们就可以使用ResultSet接口中的方法getXXX(参数)取出行中数据
            a.数据库中每行都有多列,每列数据的数据类型可能不同,想要获取数据类型对应的数据就可以使用
                int getInt(xxx); double getDouble(xxx); String getString(xxx) Date getDate() ...
            b.如果我们仅仅是想看一下查询的结果,不关系返回值类型,就可以使用
                Object getObject(xxx); String getString(xxx)
            -----------------------------------------------------------------
            int getInt(int columnIndex) : 列的索引,从1开始
            int getInt(String columnLabel) :列名 cid,cname
         */
            //int cid = rs.getInt(1);
            //String cname = rs.getString(2);
            int cid = rs.getInt("cid");
            String cname = rs.getString("cname");
            System.out.println(cid + "\t" + cname);
        }

        //5.释放资源
        JDBCUtils.close(conn, stat, rs);
    /*
        boolean b = rs.next();
        System.out.println(b);//true

        b = rs.next();
        System.out.println(b);//true

        b = rs.next();
        System.out.println(b);//true

        b = rs.next();
        System.out.println(b);//false
     */
    }

    /*
        使用JDBC技术,实现对数据库表进行删除数据
     */
    private static void delete() {
        //1.使用JDBCUtils工具类中的getConnection完成
        Connection conn = JDBCUtils.getConnection();
        //2.获取执行sql语句的执行者对象Statement
        Statement stat = null;
        try {
            //有可能产生异常的代码
            stat = conn.createStatement();
            //3.执行sql语句,获取结果
            int row = stat.executeUpdate("DELETE FROM category WHERE cid IN(4,5);");
            //4.处理结果
            if (row > 0) {
                System.out.println(row + "行数据删除成功!");
            } else {
                System.out.println("数据删除失败!");
            }
        } catch (SQLException e) {
            //异常的处理逻辑
            e.printStackTrace();
        } finally {
            //一定会执行的代码:一般用于释放资源
            //5.释放资源
            JDBCUtils.close(conn, stat, null);
        }
    }

    /*
        使用JDBC技术,实现对数据库表进行修改数据
     */
    private static void update() throws SQLException {
        //1.使用JDBCUtils工具类中的方法getConnection获取数据库连接对象Connection
        Connection conn = JDBCUtils.getConnection();
        //2.获取执行sql语句的执行者对象Statement
        Statement stat = conn.createStatement();
        //3.执行sql语句获取结果
        int row = stat.executeUpdate("UPDATE category SET cname='可乐' WHERE cid=5;");
        //4.处理结果
        System.out.println(row + "行数据修改成功!");
        //5.释放资源
        JDBCUtils.close(conn, stat, null);
    }

    /*
        使用JDBC技术,实现对数据库表进行清空数据
     */
    private static void truncate() throws SQLException {
        //1.使用JDBCUtils工具类中的方法getConnection获取数据库连接对象Connection
        Connection conn = JDBCUtils.getConnection();
        //2.获取执行sql语句的执行者对象Statement
        Statement stat = conn.createStatement();
        //3.执行sql语句获取结果
        int row = stat.executeUpdate("truncate table t_order;");
        //4.处理结果
        System.out.println("清空表成功!");
        //5.释放资源
        JDBCUtils.close(conn, stat, null);
    }

    private static void insert()  {
        //1.使用JDBCUtils工具类中的getConnection完成
        Connection conn = JDBCUtils.getConnection();
        //2.获取执行sql语句的执行者对象Statement
        Statement stat = null;
        try {
            stat = conn.createStatement();
            //3.执行sql语句,获取结果
            int row = stat.executeUpdate("INSERT INTO category (cname) VALUES('饮料');");
            //4.处理结果
            System.out.println(row + "行数据添加成功!");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //5.释放资源
            JDBCUtils.close(conn, stat, null);
        }
    }

    private static void insertBatch()  {
        //1.使用JDBCUtils工具类中的getConnection完成
        Connection conn = JDBCUtils.getConnection();
        //2.获取执行sql语句的执行者对象Statement
        PreparedStatement pstat = null;
        try {
            pstat = conn.prepareStatement("insert into t_order(order_no,user_id,item_id,order_price,status,create_time,update_time) \n" +
                    "values ( 2,1,2,11.0,1,now(),now() )");
            for (int i = 0; i < 1000000; i++) {
                pstat.addBatch();
                if (i%500==0){
                    pstat.executeBatch();
                }
            }
            pstat.executeLargeBatch();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //5.释放资源
            JDBCUtils.close(conn, pstat, null);
        }
    }

}

