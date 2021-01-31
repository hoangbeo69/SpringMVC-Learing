package com.learn.dao.impl;

import com.learn.dao.GenericDAO;
import com.learn.mapper.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AbstractDAO<T> implements GenericDAO<T> {

    ResourceBundle myBundle = ResourceBundle.getBundle("db");

    public Connection getConnection() {
        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            String url = "jdbc:mysql://localhost:3306/jspservletjdbc";
//            String user = "root";
//            String password = "123456";

            Class.forName(myBundle.getString("driverName"));
            String url = myBundle.getString("url");
            String user = myBundle.getString("user");
            String password = myBundle.getString("password");
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters) {
        List<T> results = new ArrayList<>();
        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            //set tham số cho cho câu query nếu có
            setParameter(statement, parameters);
            rs = statement.executeQuery();
            while (rs.next()) {
                results.add(rowMapper.mapRow(rs));
            }
            return results;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return null;
            }
        }
    }

    @Override
    public void update(String sql, Object... parameters) {
        PreparedStatement statement = null;
        Connection connection = null;
        try{
            connection = getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql);
            setParameter(statement,parameters);
            statement.executeUpdate();
            connection.commit();
        }catch (SQLException e){
            System.out.println(e);
            if (connection != null) {
                try {
                    connection.rollback(); //nếu 1 trong các commit phía trên thì nó sẽ quay trở về như ban đầu khi chưa insert
                } catch (SQLException throwables) {
                    System.out.println(e);
                }
            }
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public Long insert(String sql, Object... parameters) {
        ResultSet resultSet = null;
        Long id = null;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS); //thêm phần Statemen.Return_Generated..=>để có thể sủ dụng được lấy cái keyid vừa truy vấn
            setParameter(statement,parameters);
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getLong(1);
            }
            connection.commit(); //commit các câu truy vấn => các dữ liệu bắt đầu thay đổi
            return id;
        } catch (SQLException e) {
            System.out.println(e);
            if (connection != null) {
                try {
                    connection.rollback(); //nếu 1 trong các commit phía trên thì nó sẽ quay trở về như ban đầu khi chưa insert
                } catch (SQLException throwables) {
                    System.out.println(e);
                }
            }
            return null;
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return null;
            }
        }
    }

    /**
     *
     * @param sql
     * @param parameters
     */
    @Override
    public void delete(String sql, Object... parameters) {
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql);
            setParameter(statement,parameters);
            statement.executeUpdate();
            connection.commit();
        }catch (SQLException e){
            System.out.println(e);
            if (connection != null) {
                try {
                    connection.rollback(); //nếu 1 trong các commit phía trên thì nó sẽ quay trở về như ban đầu khi chưa insert
                } catch (SQLException throwables) {
                    System.out.println(e);
                }
            }
        }finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException throwables) {
                System.out.println(throwables);
            }
        }
    }

    @Override
    public int count(String sql, Object... parameters) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count = 0;
        try{
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery(sql);
            if(resultSet.next()){
               return count = resultSet.getInt(1);
            }
        }catch (SQLException e){
            System.out.println(e);
            return 0;
        }finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException throwables) {
                System.out.println(throwables);
            }
        }
        return  0;
    }

    /**
     * @param statement
     * câu lênh statemet query
     * @param parameters
     * các tham số truyền vào
     * @apiNote
     *hàm này sử dụng khi sử dụng preparedStatement có các tham số được truyền vào là các parameters
     * hàm này có nhiệm vụ là sẽ tự động kiểm tra kiểu biến được gửi vào là kiểu gì và tự set statement.set{kiểu dữ liệu}({index},parameters)
     */
    private void setParameter(PreparedStatement statement, Object... parameters) {
        try {
            for (int count = 0; count < parameters.length; count++) {
                Object parameter = parameters[count];
                int index = count + 1;
                if (parameter instanceof Long) {
                    statement.setLong(index, (Long) parameter);
                } else if (parameter instanceof Integer) {
                    statement.setInt(index, (Integer) parameter);
                } else if (parameter instanceof Float) {
                    statement.setFloat(index, (Float) parameter);
                } else if (parameter instanceof String) {
                    statement.setString(index, (String) parameter);
                } else if (parameter instanceof Timestamp){
                    statement.setTimestamp(index,(Timestamp) parameter);
                } else if(parameter == null){
                    statement.setNull(index,Types.NULL);
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
