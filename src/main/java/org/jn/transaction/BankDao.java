package org.jn.transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

public class BankDao {
    public void add(String account, int money, Connection connection) throws
            ClassNotFoundException, SQLException {
        String sql = "update t_bank set money = money + ? where account = ? ;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,money);
        preparedStatement.setString(2,account);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        System.out.println("加钱成功");
    }
    public void sub(String account,int money,Connection connection) throws SQLException,
            ClassNotFoundException {
        String sql = "update t_bank set money = money - ? where account = ? ;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,money);
        preparedStatement.setString(2,account);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        System.out.println("减钱成功");
    }
}
