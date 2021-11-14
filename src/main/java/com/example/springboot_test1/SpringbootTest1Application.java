package com.example.springboot_test1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
@SpringBootApplication annotation으로 인하여 이 class가 메인 클래스가 됨.
항상 최상단에 위치해 있어야함.
 */
@SpringBootApplication
public class SpringbootTest1Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootTest1Application.class, args);  // 위 run으로 인하여 내장 WAS가 동작하게 됨.
    }

}
