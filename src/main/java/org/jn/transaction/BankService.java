package org.jn.transaction;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BankService {
    @Test
    public void start() throws SQLException, ClassNotFoundException {
        transfer("lvdandan","ergouzi",500);
    }
    public void transfer(String addAccount,String subAccount,int money) throws SQLException,
            ClassNotFoundException {
        BankDao bankDao = new BankDao();
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql:///atguigu?user=root" +
                "&password=000000");
        try {
            connection.setAutoCommit(false);
            bankDao.add(addAccount,money,connection);
            System.out.println("--------------------------------");
            bankDao.sub(subAccount,money,connection);
            connection.commit();
        }catch (Exception e) {
            connection.rollback();
            throw e;
        }finally {
            connection.close();
        }
    }
}
