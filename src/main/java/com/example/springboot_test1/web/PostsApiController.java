package com.example.springboot_test1.web;

import com.example.springboot_test1.service.posts.PostsService;
import com.example.springboot_test1.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }
}


/*
    controller -> service -> Repository Interface(자동으로 사용가능, save, find이런것들을 사용할 수 있음.)
 */