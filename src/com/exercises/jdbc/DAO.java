package com.exercises.jdbc;

import org.apache.commons.beanutils.BeanUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DAO {
    public void update(String sql, Object... args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = JDBCTools.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(null, preparedStatement, connection);
        }
    }

    public <T> T get(Class<T> clazz, String sql, Object... args) {
        T entity = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = JDBCTools.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Map<String, Object> values = new HashMap<String, Object>();
                ResultSetMetaData rsmd = resultSet.getMetaData();
                int columnCount = rsmd.getColumnCount();

                for (int i = 0; i < columnCount; i++) {
                    String columnLabel = rsmd.getColumnLabel(i + 1);
                    Object columnValue = resultSet.getObject(columnLabel);

                    values.put(columnLabel, columnValue);
                }

                entity = clazz.newInstance();

                for (Map.Entry<String, Object> entry : values.entrySet()) {
                    String propertyName = entry.getKey();
                    Object value = entry.getValue();

//                    ReflectionUtils.setFieldValue(entity, propertyName, value);
                    BeanUtils.setProperty(entity, propertyName, value);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(resultSet, preparedStatement, connection);
        }

        return entity;
    }

    public <T> List<T> getForList(Class<T> clazz, String sql, Object... args) {
        List<T> list = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = JDBCTools.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }

            resultSet = preparedStatement.executeQuery();

            List<Map<String, Object>> values = new ArrayList<>();

            ResultSetMetaData rsmd = resultSet.getMetaData();
            Map<String, Object> map = null;

            while (resultSet.next()) {
                map = new HashMap<>();
                for (int i = 0; i < rsmd.getColumnCount(); i++) {
                    String columnLabel = rsmd.getColumnLabel(i + 1);
                    Object value = resultSet.getObject(i + 1);

                    map.put(columnLabel, value);
                }

                values.add(map);
            }

            T bean = null;
            if (values.size() > 0) {
                for (Map<String, Object> m : values) {
                    bean = clazz.newInstance();

                    for (Map.Entry<String, Object> entry : m.entrySet()) {
                        String propertyName = entry.getKey();
                        Object value = entry.getValue();

                        BeanUtils.setProperty(bean, propertyName, value);
                    }

                    list.add(bean);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(resultSet, preparedStatement, connection);
        }

        return list;
    }
}
