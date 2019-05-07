package com.kellybatis.mapper;

import com.kellybatis.entity.Blog;

public interface BlogMapper {


    /**
     * 根据主键BID查询Blog
     * @param bid
     * @return
     */
    public Blog selectBlogById(Integer bid);

}
