package com.example.springboot_test1.web;

import com.example.springboot_test1.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// RestController 는 JSON으로 반환하는 컨트롤로 만들어줌, 이전에 메소드 위 마다 사용하였던 @ResponseBody를 대체함.
@RestController
public class HelloController {

    // HTTP Get요청을 방을 수 있는 API를 만들어줌
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(
            @RequestParam("name") String name,  // 외부에서 API넘긴 파라미터를 가져오는 어노테이션
            @RequestParam("amount") int amount
    ){
        return new HelloResponseDto(name,amount);
    }
}
