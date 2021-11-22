package com.example.springboot_test1.web.dto;

import com.example.springboot_test1.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;

    public PostsResponseDto(Posts entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}

/*
    Entity 의 일부만 사용하며로 생성자로 Entity를 받아서 필드에 넣는다.
 */