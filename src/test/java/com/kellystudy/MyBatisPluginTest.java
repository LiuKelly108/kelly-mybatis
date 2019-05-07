package com.kellystudy;

import com.kellystudy.domain.Blog;
import com.kellystudy.mapper.BlogMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyBatisPluginTest {

    private SqlSessionFactory sqlSessionFactory ;

    @Before
    public void prepare() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        inputStream.close();
    }


    /**
     * MyBatis的翻页插件测试：KYPageInterceptor 和 KYSQLExecuteTimeInterceptor
     */
    @Test
    public void testSelectBlogList(){

        SqlSession sqlSession = sqlSessionFactory.openSession();
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);

        int startIndex= 0;
        int countLimit=2 ;
        RowBounds rs = new RowBounds(startIndex,countLimit) ;
        List<Blog> blogList = blogMapper.selectBlogList(rs);

        for (Blog blog : blogList){
            System.out.println(blog.toString());
        }
    }
}
