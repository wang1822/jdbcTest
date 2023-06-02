package org.jn.statement;

import com.mysql.cj.jdbc.Driver;

import java.sql.*;

public class statementTest {
    public static void main(String[] args) throws SQLException {
        DriverManager.registerDriver(new Driver());
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/atguigu",
                "root", "000000");
        Statement statement = connection.createStatement();
        String sql = "select * from t_user;";
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String account = resultSet.getString("account");
            String password = resultSet.getString("password");
            String nickname = resultSet.getString("nickname");
            System.out.println(id + "--" + account + "--" + password + "--" + nickname);
        }
        resultSet.close();
        statement.close();
        connection.close();
    }
}
