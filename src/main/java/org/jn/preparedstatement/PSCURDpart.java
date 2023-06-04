package org.jn.preparedstatement;

import org.junit.Test;
import sun.plugin2.gluegen.runtime.CPU;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PSCURDpart {
    @Test
    public void testInsert() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.
                getConnection("jdbc:mysql:///atguigu", "root", "000000");
        String sql = "insert into t_user (account,password,nickname) values (?,?,?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1,"test");
        preparedStatement.setObject(2,"test");
        preparedStatement.setObject(3,"二狗子");
        int rows = preparedStatement.executeUpdate();
        if (rows > 0){
            System.out.println("插入成功");
        }else{
            System.out.println("插入失败");
        }
        preparedStatement.close();
        connection.close();
    }

    @Test
    public void testUpdate() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.
                getConnection("jdbc:mysql:///atguigu", "root", "000000");
        String sql = "update t_user set nickname = ? where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1,"三狗子");
        preparedStatement.setObject(2,"3");
        int i = preparedStatement.executeUpdate();
        if (i > 0){
            System.out.println("更新成功");
        }else {
            System.out.println("更新失败");
        }
        preparedStatement.close();
        connection.close();
    }

    @Test
    public void testDelete() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.
                getConnection("jdbc:mysql:///atguigu", "root", "000000");
        String sql = "delete from t_user where id =?";
        PreparedStatement preparedstatement = connection.prepareStatement(sql);
        preparedstatement.setObject(1,3);
        int  i = preparedstatement.executeUpdate();
        if (i > 0){
            System.out.println("数据删除成功！");
        }else {
            System.out.println("数据删除失败");
        }
        preparedstatement.close();
        connection.close();

    }

    @Test
    public void testSelect() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.
                getConnection("jdbc:mysql:///atguigu", "root", "000000");
        String sql = "select * from t_user ;";
        PreparedStatement preparedstatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedstatement.executeQuery();
        List<Map> list = new ArrayList<Map>();
        ResultSetMetaData metaData = resultSet.getMetaData();//装的当前对象集的列信息
        int columnCount = metaData.getColumnCount();

        while (resultSet.next()){
            Map map = new HashMap();
//            map.put("id",resultSet.getInt("id"));
//            map.put("account",resultSet.getString("account"));
//            map.put("password",resultSet.getString("password"));
//            map.put("nickname",resultSet.getString("nickname"));
            for (int i = 1; i <= columnCount; i++) {
                Object value = resultSet.getObject(i);
                String columnLabel = metaData.getColumnLabel(i);
                map.put(columnLabel, value);
            }
            list.add(map);
        }
        System.out.println("list = " + list);
        preparedstatement.close();
        connection.close();
    }
    public static void main(String[] args) {
    }
}
