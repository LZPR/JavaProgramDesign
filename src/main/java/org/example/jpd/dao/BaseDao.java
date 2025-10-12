package org.example.jpd.dao;

import org.example.jpd.common.exception.DatabaseException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 为方便资源管理，在实验的基础上实现了{@link AutoCloseable}接口。
 * <p>被实例化时会自动连接数据库，关闭时自动断开连接。
 */
public class BaseDao implements AutoCloseable {

    public BaseDao() throws ClassNotFoundException, SQLException {
        connect();
    }

    public Connection getConnection() {
        return connection;
    }

    private Connection connection;

    public void connect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(System.getenv("JDBC_URL"));
    }

    public void disconnect() throws SQLException {
        connection.close();
    }

    @Override
    public void close() throws SQLException {
        disconnect();
    }
}
