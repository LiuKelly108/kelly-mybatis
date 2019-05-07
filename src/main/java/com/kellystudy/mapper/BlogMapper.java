package com.kellystudy.mapper;

import com.kellystudy.domain.Blog;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface BlogMapper {

    /**
     * 根据ID查询Blog
     * @param id
     * @return
     */
    public Blog selectBlogById(Integer id) ;

    /**
     * 根据文章列表翻页查询
     * @param rs
     * @return
     */
    public List<Blog> selectBlogList(RowBounds rs) ;

    /**
     * 新增Blog
     * @param blog
     * @return
     */
    public int insertBlog(Blog blog) ;

    /**
     * 根据主键更新Blog
     * @param blog
     * @return
     */
    public int updateBlogByPrimaryKey(Blog blog);
}
