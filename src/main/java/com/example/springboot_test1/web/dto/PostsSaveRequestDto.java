package com.example.springboot_test1.web.dto;

import com.example.springboot_test1.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title,String content,String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}

/*
    Dto는 빈번하게 변경될 수 있지만
    절대로 Entity는 변경이 자주 일어나서는 안된다. ( view Layer 와 DB Layer의 역활 분리를 철저하게 하는게 좋다)
    Controller에서 결과값으로 여러 테이블을 조인해야하는 경우가 많다.
 */