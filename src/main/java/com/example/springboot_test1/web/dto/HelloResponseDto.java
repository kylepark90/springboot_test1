package com.example.springboot_test1.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
/*
lombok를 사용하기 위해서는 preference > compiler >Annotation processor 에서 Enable annotation processing 에 체크를 해주어야한다.

 */
@Getter // 선언된 모든 필드의 get 메소드를 생성해줍니다.
@RequiredArgsConstructor
public class HelloResponseDto {
    private final String name;
    private final int amount;
}
