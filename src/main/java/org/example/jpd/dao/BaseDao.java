package org.example.jpd.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDao {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void connect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 从环境变量中读取数据库的连接地址，方便在不同环境中切换数据库
        connection = DriverManager.getConnection(System.getenv("JDBC_URL"));
    }

    public void disconnect() throws SQLException {
        connection.close();
    }
}
