// 
// 
// 

package com.sa.util;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import javax.sql.DataSource;

public class JDBCUtils
{
    private static DataSource dataSource;
    
    static {
        JDBCUtils.dataSource = null;
        JDBCUtils.dataSource = (DataSource)new ComboPooledDataSource("c3p0");
    }
    
    public static Connection getConnection() throws Exception {
        return JDBCUtils.dataSource.getConnection();
    }
    
    public static void releaseDB(final Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void releaseDB(final ResultSet resultSet, final Statement statement) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (statement != null) {
                statement.close();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
