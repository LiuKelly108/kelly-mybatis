package com.kellybatis.v1;

import java.sql.SQLException;
import java.util.Optional;

/**
 * 应用层的API,定义基本数据库的操作
 *
 */
public class KYSqlSession {

    private KYConfiguration  configuration ; //用于保存配置文件信息（包括接口的方法），获取代理对象
    private KYExecutor  executor ; //用于执行数据库的操作

    public KYSqlSession(KYConfiguration configuration ,KYExecutor executor){
        this.configuration = configuration;
        this.executor = executor ;
    }
    

    /**
     * 获得一个Mapper类的代理对象
     * @param clazz  Mapper类class
     * @return  返回对应Mapper的代理对象
     */
    public <T>T getMapper(Class clazz) throws ClassNotFoundException {
        return configuration.getMapper(clazz,this);
    }
    /**
     *  执行Sql
     * @param statementID  SQL的ID
     * @param paramter     SQL的参数
     * @param <T>          返回任意类型
     */
    public <T>T  selectOne(String statementID,Object paramter) throws SQLException {

        String sql = KYConfiguration.sqlMappings.getString(statementID);
        if(!sql.isEmpty()){
            return executor.query(sql,paramter);
        }
        return null;
    }


}
