package com.kellybatis.entity;

import lombok.Data;

@Data
public class Blog {
    Integer bid; // 文章ID
    String name; // 文章标题
    Integer author_id; // 文章作者ID

    public String toString() {
        return "Blog{" +
                "bid=" + bid +
                ", name='" + name + '\'' +
                ", authorId='" + author_id + '\'' +
                '}';
    }
}
