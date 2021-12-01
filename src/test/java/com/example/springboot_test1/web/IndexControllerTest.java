package com.example.springboot_test1.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

/*
    spring 3.X 에서는 RestTemplate을 이용하여 Rest API호출 -> 동기식
    하지만 Spring 5이상 버전에서는 WebClient 지원 -> 비동기
 */
//@MockBean(JpaMetamodelMappingContext.class) // test중에 데이터베이스를 사용하지 않아서 스프링이 발생시키는 예외 , mockBean annotation추가해 주면됨.
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class IndexControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void loading_main_page(){
        //when
        String body = this.restTemplate.getForObject("/", String.class);    // 이런식으로 호출하면 html안에 값이 아닌 controller에서 return한 머스테치 파일이름을 가져옴.
        //ResponseEntity<String> responseEntity = this.restTemplate.getForEntity("/", String.class, 25);

        //then
        //assertThat(body).contains("스프링 부트로 시작하는 웹 서비스");    // error body값은 index(controller에서 리턴한 view이름)를 받아오고 있음
        //assertThat(responseEntity.getBody()).contains("스프링 부트로 시작하는 웹 서비스");
        assertThat(body).contains("index");
    }
}

