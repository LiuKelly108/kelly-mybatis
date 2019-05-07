package com.kellystudy.interceptor;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;

import java.sql.Statement;
import java.util.Properties;

/**
 * 插件作用：查询SQL的执行语句的时间
 */

@Intercepts({ @Signature(type = StatementHandler.class, method = "query", args = { Statement.class, ResultHandler.class}) })
public class KYSQLExecuteTimeInterceptor  implements Interceptor {

    /**
     * 插件的拦截方法
     * @param invocation
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("进入KYSQLExecuteTimeInterceptor的拦截方法");
        //1.获得SQL
        StatementHandler statementHandler = (StatementHandler)invocation.getTarget();
        String sql= statementHandler.getBoundSql().getSql();
        System.out.println("获取到的SQL:"+sql);

        long startTime=System.currentTimeMillis();
        System.out.println("SQL开始时间："+startTime);
        try {
            return invocation.proceed();
        }finally {
            long endTime=System.currentTimeMillis();
            System.out.println("SQL结束时间："+endTime);
            System.out.println("共耗费执行时间为："+(endTime - startTime)+"ms");
        }
    }

    @Override
    public Object plugin(Object target) {
        System.out.println("进入 KYSQLExecuteTimeInterceptor.plugin()");
        return Plugin.wrap(target,this);
    }

    @Override
    public void setProperties(Properties properties) {
        System.out.println("进入 KYSQLExecuteTimeInterceptor.setProperties()");
    }
}
