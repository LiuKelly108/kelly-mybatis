package com.kellybatis;

import com.kellybatis.entity.Blog;
import com.kellybatis.mapper.BlogMapper;
import com.kellybatis.v1.KYConfiguration;
import com.kellybatis.v1.KYExecutor;
import com.kellybatis.v1.KYSqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MyBatisTest {

    private KYSqlSession  sqlSession ;

    @Before
    public void prepare(){
        KYConfiguration configuration = new KYConfiguration();
        KYExecutor executor = new KYExecutor() ;
        sqlSession = new KYSqlSession(configuration,executor);
    }

    @After
    public void after(){
        if (sqlSession != null){
            sqlSession = null;
        }
    }

    /**
     * 测试kellybatis的v1版本
     */
    @Test
    public void  testKellyBatisV1() throws ClassNotFoundException {
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        Blog blog = mapper.selectBlogById(1);
        System.out.println(blog.toString());
    }
}
