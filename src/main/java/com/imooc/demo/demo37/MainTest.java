package com.imooc.demo.demo37;

import java.sql.*;

public class MainTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javabook" +
                "?user=xiaoK&password=LJYX97010208&useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true");
        PreparedStatement preparedStatement = connection.prepareStatement("select * from student");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            System.out.println(
                    resultSet.getString(1) + " " +
                    resultSet.getString(2) + " " +
                    resultSet.getString(3) + " " +
                    resultSet.getString(4) + " " +
                    resultSet.getString(5) + " " +
                    resultSet.getString(6) + " " +
                    resultSet.getString(7) + " " +
                    resultSet.getString(8) + " " +
                    resultSet.getString(9)
            );
        }
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        System.out.println(databaseMetaData.getDatabaseProductName());
    }
}
