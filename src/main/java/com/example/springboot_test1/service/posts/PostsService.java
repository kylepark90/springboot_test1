package com.example.springboot_test1.service.posts;

import com.example.springboot_test1.domain.posts.Posts;
import com.example.springboot_test1.domain.posts.PostsRepository;
import com.example.springboot_test1.web.dto.PostsListResponseDto;
import com.example.springboot_test1.web.dto.PostsResponseDto;
import com.example.springboot_test1.web.dto.PostsSaveRequestDto;
import com.example.springboot_test1.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(()->new
                IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당게시글이 없습니다. id="+id)); // 존재하는 아이디인지 체크
        postsRepository.delete(posts);  // repository에 지원하는 delete이용
    }

    public PostsResponseDto findById (Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(()->new
                IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        return new PostsResponseDto(entity);
    }
/*
    postsRepository 결과로 넘어온 Posts의 Stream을 map을 통해 PostsListResponseDto 변환 -> List로 반환하는 메소드.
 */
    @Transactional(readOnly = true)                         // transactional readOnly = true 옵션을 주면 트랜잭션 범위는 유지하되, 조회기능만 남겨두어 조회 속도 개선(수정,삭제 기능이 없믐 서비스 메소드에서 사용)
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)         // .map(posts -> new PostsListResponseDto(posts)) 랑 같다.
                .collect(Collectors.toList());
    }
}


/*
    스프링에서 Bean을 주입받는 방식이 3가지가 있다 (@Autowired, setter, 생성자)
    이중 생성자로 주입을 받는 방식이 좋다 -> 순환 참조를 막기 위해서라도
    생성자는 @RequiredArgsConstructor에서 생성을 해준다.
    final이 선언된 모든 필드를 인자값으로 하는 생성자를 롬복의 @RequiredArgsConstructor 가 대신 생성해준다.
    -> 롬복을 쓰면 해당 클래스의 의존성 관계가 변경될 때마다 생성자 코드를 계속해서 수정하는 번거로움을 해결
 */

/*
    더티 체킹 : Entity객체의 값만 변경하면 별도로 update쿼리를 날리지않아도 트랜잭션이 끝나는 시점에 해당 테이블에 변경분을 반영한다.
 */