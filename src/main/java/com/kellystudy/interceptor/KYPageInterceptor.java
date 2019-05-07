package com.kellystudy.interceptor;


import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.Field;
import java.util.Properties;

/***
 * 编写插件的步骤：
 * 1、定义插件：implements Interceptor
 *    添加@Interceptor注解
 * 2、mybatis-config.xml 注册插件
 * 3、编写插件的业务逻辑方法
 */

/**
 * 插件作用：查询SQL的翻页操作
 */
@Intercepts(@Signature(type = Executor.class ,method = "query",
        args = {MappedStatement.class,Object.class , RowBounds.class , ResultHandler.class}))
public class KYPageInterceptor implements Interceptor {

    /**
     * 插件的拦截方法
     * @param invocation
     * @return
     * @throws Throwable
     */

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("-----进入 KYPageInterceptor.interceptor(Invocation)插件拦截器方法中-------");
        // 如果要进行分页的封装，则需要获得 查询Sql,分页的参数
        Object[] args = invocation.getArgs();
        //获得MappedStatememt
        MappedStatement mappedStatement = (MappedStatement) args[0];
        //获得Sql
        BoundSql boundSql = mappedStatement.getBoundSql(args[1]);
        String sql = boundSql.getSql();
        System.out.println("原方法中的sql:"+sql);
        //获得翻页相关的参数：当前页标、每次查询的数目
        RowBounds rowBounds =(RowBounds) args[2];
        int startIndex = rowBounds.getOffset();//当前的页标
        int countlimit = rowBounds.getLimit();//每次查询的数目
        System.out.println("---startIndex="+startIndex+",countlimit="+countlimit);
        //----------参数获取完成开始处理-------
        //1.如果分页中无参数，则不处理
        if( RowBounds.DEFAULT ==rowBounds ){
            return invocation.proceed();
        }

        //2.修改Sql,使其变为可翻页的查询
        String sql_limit = String.format("LIMIT %d ,%d ", startIndex, countlimit);
        sql =sql+" "+sql_limit ;
        System.out.println("-----sql:"+sql);

        //3.根据新的Sql 创建SqlSource
        SqlSource sqlSource = new StaticSqlSource(mappedStatement.getConfiguration(),sql, boundSql.getParameterMappings());
        //使用反射修改mappedstatement中的SqlSource
        Field sqlSource1_field = mappedStatement.getClass().getDeclaredField("sqlSource");
        sqlSource1_field.setAccessible(true);
        sqlSource1_field.set(mappedStatement,sqlSource);

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        System.out.println("-----进入到KYPageInterceptor.plugin("+target+")-------");
        return Plugin.wrap(target,this);
    }

    @Override
    public void setProperties(Properties properties) {
        System.out.println("-----------进入到KYPageInterceptor.setProperties("+properties+")------");
    }
}
