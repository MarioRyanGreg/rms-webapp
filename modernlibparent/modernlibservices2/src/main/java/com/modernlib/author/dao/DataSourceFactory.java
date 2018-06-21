package com.modernlib.author.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSourceFactory {

    public static Connection getConnection() throws SQLException {
        //return SingletonHelper.INSTANCE.dataSource.getConnection();

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modernlibrary?useSSL=false&serverTimezone=UTC", "student", "student");
        return conn;
    }
}
