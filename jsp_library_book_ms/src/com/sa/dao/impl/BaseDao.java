// 
// 
// 

package com.sa.dao.impl;

import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import java.util.List;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Statement;
import com.sa.util.JDBCUtils;
import com.sa.web.ConnectionContext;
import com.sa.util.ReflectionUtils;
import org.apache.commons.dbutils.QueryRunner;
import com.sa.dao.Dao;

public class BaseDao<T> implements Dao<T>
{
    private QueryRunner queryRunner;
    private Class<T> clazz;
    
    public BaseDao() {
        this.queryRunner = new QueryRunner();
        this.clazz = ReflectionUtils.getSuperGenericType(this.getClass());
    }
    
    @Override
    public long insert(final String sql, final Object... args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        long id = 0L;
        try {
            connection = ConnectionContext.getInstance().get();
            preparedStatement = connection.prepareStatement(sql, 1);
            if (args != null) {
                for (int i = 0; i < args.length; ++i) {
                    preparedStatement.setObject(i + 1, args[i]);
                }
            }
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getLong(1);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return id;
        }
        finally {
            JDBCUtils.releaseDB(resultSet, preparedStatement);
        }
        JDBCUtils.releaseDB(resultSet, preparedStatement);
        return id;
    }
    
    @Override
    public void update(final String sql, final Object... args) {
        Connection connection = null;
        try {
            connection = ConnectionContext.getInstance().get();
            this.queryRunner.update(connection, sql, args);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public T query(final String sql, final Object... args) {
        Connection connection = null;
        try {
            connection = ConnectionContext.getInstance().get();
            return (T)this.queryRunner.query(connection, sql, (ResultSetHandler)new BeanHandler((Class)this.clazz), args);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public List<T> queryForList(final String sql, final Object... args) {
        Connection connection = null;
        try {
            connection = ConnectionContext.getInstance().get();
            return (List<T>)this.queryRunner.query(connection, sql, (ResultSetHandler)new BeanListHandler((Class)this.clazz), args);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public <V> V getSingleVal(final String sql, final Object... args) {
        Connection connection = null;
        try {
            connection = ConnectionContext.getInstance().get();
            return (V)this.queryRunner.query(connection, sql, (ResultSetHandler)new ScalarHandler(), args);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public void batch(final String sql, final Object[]... params) {
        Connection connection = null;
        try {
            connection = ConnectionContext.getInstance().get();
            this.queryRunner.batch(connection, sql, params);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
