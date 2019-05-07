package com.kellystudy.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Blog implements Serializable {
    private static final long serialVersionUID = -1208456246364858020L;

    private Integer bid ; //文章ID
    private String name; //文章标题
    private Integer authorId ; //文章作者

    public String toString(){
        return "Blog{" +
                "bid=" + bid +
                ", name='" + name + '\'' +
                ", authorId='" + authorId + '\'' +
                '}';
    }
}
