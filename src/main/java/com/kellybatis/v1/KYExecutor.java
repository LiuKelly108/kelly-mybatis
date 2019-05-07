package com.kellybatis.v1;

import com.kellybatis.entity.Blog;

import java.lang.reflect.Field;
import java.net.ConnectException;
import java.sql.*;

/**
 * 执行器，用于封装JDBC,操作数据库
 */
public class KYExecutor {

    /**
     * 获得连接Connection
     * @return Connection
     */
    private Connection getConnection(){
        Connection conn = null;
        // 注册 JDBC 驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // 打开连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gp-mybatis?useUnicode=true&characterEncoding=utf-8&rewriteBatchedStatements=true&serverTimezone=UTC",
                    "root", "123456");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return  conn ;
    }

    /**
     * 获得Statement
     * @param connection
     * @return
     * @throws ConnectException
     */
    private Statement getStatement(Connection connection) throws ConnectException {
        Statement statement = null ;
        if (connection !=null){
            try {
                statement = connection.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else {
            throw  new ConnectException("connection == null ");
        }
        return  statement ;
    }

    /**
     * 将结果集转换为obj
     * @param rs
     * @param obj
     * @param <T>
     * @return
     */
    private <T> T resultSetToT(ResultSet rs, Object obj){

        if(null == rs ){
            return  null ;
        }

        Class<?> clazz = obj.getClass();
        try{
            while (rs.next()){
                Field[] fields = clazz.getDeclaredFields();
                for (Field field :fields){

                    field.setAccessible(true);

                    if(field.getType() == Integer.class){
                        Integer value =  rs.getInt(field.getName());
                        field.set(obj,value);
                    }else if(field.getType() == String.class){
                        String value =rs.getString(field.getName());
                        field.set(obj,value);
                    }
                }
            }
        }catch (Exception e){
           e.printStackTrace();
        }
        return  (T)obj ;
    }


    /**
     * 关闭Connection/Statment/ResultSet
     * @param conn
     * @throws SQLException
     */
    public void closeResultSetStatmentConn(ResultSet rs, Statement statement, Connection conn) throws SQLException {

        if( rs!=null){
            rs.close();
        }
        if (statement !=null){
            statement.close();
        }
        if( null != conn || !conn.isClosed()){
            conn.close();
        }
    }
    /**
     * 查询操作
     * @param sql
     * @param paramter
     * @param <T>
     * @return
     */

    public <T>T  query(String sql, Object paramter) throws SQLException {

        Connection connection = getConnection();
        Statement statement = null ;
        ResultSet rs = null ;
        Blog blog = new Blog();

        try {
            statement = getStatement(connection);
            rs = statement.executeQuery(String.format(sql, paramter));
            blog  = resultSetToT(rs, blog);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeResultSetStatmentConn(rs,statement,connection);
        }

        return (T) blog;
    }
}
