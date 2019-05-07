package com.kellybatis.v1;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 代理对象（jdk代理方式）
 */

public class KYMapperProxy implements InvocationHandler {

    private KYSqlSession  sqlSession ;


    public KYMapperProxy(KYSqlSession sqlSession){
        this.sqlSession = sqlSession ;
    }
    /**
     * 代理方法
     * @param proxy    代理对象
     * @param method  代理对象的方法
     * @param args    代理对象的参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        //根据代理对象的方法名和参数，可以获得StatementId 和参数
        String mapperInterfaces = method.getDeclaringClass().getName();
        String methodName = method.getName() ;
        String statementId=mapperInterfaces+"."+methodName;

        return sqlSession.selectOne(statementId,args[0]);
    }
}
