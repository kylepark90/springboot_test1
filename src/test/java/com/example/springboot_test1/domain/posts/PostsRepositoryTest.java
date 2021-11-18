package com.example.springboot_test1.domain.posts;

import org.junit.Test;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/*
    별다른 설정 없이 @SpringBootTest를 사용할 경우 H2 데이터베이스를 자동으로 실행해 준다.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After      // Junit 에서 단위 테스트가 끝날 때마다 수행되는 메소드 지정 -> 데이터 input테스트를 하였다면 input 한 데이터를 지워준다.
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void bind_boardData(){
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder()    // postsRepository.save 테이블 posts에 insert/update 쿼리를 실행, id가 있다면 update, 없다면 insert 쿼리 실행
                .title(title)
                .content(content)
                .author("test@gmail.com")
                .build());

        //When
        List<Posts> postsList = postsRepository.findAll();  // 테이블 posts에 있는 모든 데이터를 조회해오는 메소드

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }
}
