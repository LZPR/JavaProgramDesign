package org.example.jpd.common.util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Supplier;

/**
 * 数据库工具类，添加了 SQL 操作的快捷方法。
 */
public class SqlUtil {

    public static void fillStatement(PreparedStatement statement, Supplier<?>... suppliers) throws SQLException {
        for (int i = 0; i < suppliers.length; i++) {
            Object value = suppliers[i].get();
            statement.setObject(i + 1, value);
        }
    }

    public static boolean isColumnExists(ResultSet rs, String columnName) {
        try {
            rs.findColumn(columnName);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
