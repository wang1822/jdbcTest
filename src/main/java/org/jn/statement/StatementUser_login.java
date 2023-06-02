package org.jn.statement;


import java.sql.*;
import java.util.Scanner;

public class StatementUser_login {
    public static void main(String[] args) throws
            SQLException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入账号");
        String account = scanner.nextLine();
        System.out.println("请输入密码");
        String password = scanner.nextLine();

//        DriverManager.registerDriver(new Driver());
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection("jdbc:mysql:///atguigu",
                "root", "000000");

        String sql = "select * from t_user where account = '"+account+"' and password = '"+password+"';";

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()){
            int id = resultSet.getInt(1);
            String account1 = resultSet.getString(2);
            String password2 = resultSet.getString(3);
            String nickname = resultSet.getString(4);
            System.out.println(nickname);
        }

        resultSet.close();
        statement.close();
        connection.close();
    }
}
