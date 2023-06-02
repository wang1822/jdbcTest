package org.jn.statement;

import com.mysql.cj.jdbc.Driver;

import java.sql.DriverManager;
import java.sql.SQLException;
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

        DriverManager.getConnection("jdbc:mysql:///atguigu",
                "root","000000");


    }
}
