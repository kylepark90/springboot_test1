package com.example.springboot_test1.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

/*
    JPA repository 는 Mybatis Dao역활을 하는데 인터페이스를 생성한뒤에
    extends 로 JpaRepository<Entity class, pk타입>를 상속하면 기본적인 CRUD 메소드가 자동으로 생성된다.
    굳이 @Repository를 추가할 필요는 없다.
    하지만 Entity클래스와 Entity Repository는 같은 곳에 위치해야함. (Entity 클래스 역시 기본 Repository없이는 제대로 역활을 할 수 없음)

 */
public interface PostsRepository extends JpaRepository<Posts,Long>{

}
