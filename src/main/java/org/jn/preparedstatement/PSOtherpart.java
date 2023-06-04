package org.jn.preparedstatement;


import org.junit.Test;

import java.sql.*;

public class PSOtherpart {
    @Test
    public void returnPrimary() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql:///" +
                "atguigu?user=root&password=000000");
        String sql = "insert into t_user(account,password,nickname) values (?,?,?);";
        PreparedStatement preparedstatement = connection.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS);
        preparedstatement.setObject(1,"test");
        preparedstatement.setObject(2,"123456");
        preparedstatement.setObject(3,"驴蛋蛋");
        int i = preparedstatement.executeUpdate();
        if (i > 0){
            System.out.println("插入成功");
            ResultSet resultSet = preparedstatement.getGeneratedKeys();
            resultSet.next();
            int anInt = resultSet.getInt(1);
            System.out.println("id = " + anInt);
            resultSet.close();
        }else{
            System.out.println("插入失败");
        }
        preparedstatement.close();
        connection.close();
    }

    @Test
    public void testInsert() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql:///" +
                "atguigu?user=root&password=000000");
        String sql = "insert into t_user(account,password,nickname) values (?,?,?);";
        PreparedStatement preparedstatement = connection.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            preparedstatement.setObject(1,"dd" + i);
            preparedstatement.setObject(2,"dd" + i);
            preparedstatement.setObject(3,"驴蛋蛋" + i);
            preparedstatement.executeUpdate();
        }
        long end = System.currentTimeMillis();
        System.out.println("共消耗" + (end - start) + "ms");
        preparedstatement.close();
        connection.close();
    }

    @Test
    public void testInsertB() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql:///" +
                "atguigu?user=root&password=000000");
        String sql = "insert into t_user(account,password,nickname) values (?,?,?)";
        PreparedStatement preparedstatement = connection.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            preparedstatement.setObject(1,"ddd" + i);
            preparedstatement.setObject(2,"ddd" + i);
            preparedstatement.setObject(3,"驴蛋蛋ddd" + i);
            preparedstatement.addBatch();//不执行，追加到values后面，统一执行
        }
        preparedstatement.executeBatch();//统一执行
        long end = System.currentTimeMillis();
        System.out.println("共消耗" + (end - start) + "ms");
        preparedstatement.close();
        connection.close();
    }
}
