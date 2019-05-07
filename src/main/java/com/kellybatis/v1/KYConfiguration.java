package com.kellybatis.v1;

import lombok.Data;

import java.lang.reflect.Proxy;
import java.util.ResourceBundle;

/**
 * 配置类Configuration
 */
public class KYConfiguration {

    private  static final String sqlPropertiesName = "kysql";
    public  static final ResourceBundle  sqlMappings ;


    static {
        sqlMappings = ResourceBundle.getBundle(sqlPropertiesName);
    }


    /**
     * 创建代理对象
     * @param clazz
     * @return
     */
    public <T>T getMapper(Class clazz,KYSqlSession sqlSession) throws ClassNotFoundException {

        if(!clazz.isInterface()){
            throw  new  ClassNotFoundException(clazz+"应当是一个interface");

        }
        Object obj = Proxy.newProxyInstance(this.getClass().getClassLoader(),   new Class[]{clazz}, new KYMapperProxy(sqlSession));

        return (T)obj ;
    }
}
