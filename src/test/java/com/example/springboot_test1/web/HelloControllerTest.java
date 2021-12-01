package com.example.springboot_test1.web;

/*
Test 에서 No tests found for given includes: filter.includeTestsMatching 이러한 오류가 뜨면
Settings > Build,Execution,Deployment > Build Tools > Gradle > "Run tests using:  IntelliJ IDEA" 로 변경처리가 필요
출처: https://ddasi-live.tistory.com/35
 */

import com.example.springboot_test1.web.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.is;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@MockBean(JpaMetamodelMappingContext.class) // test중에 데이터베이스를 사용하지 않아서 스프링이 발생시키는 예외 , mockBean annotation추가해 주면됨.
@RunWith(SpringRunner.class) // 테스트를 진행할때 Junit에 내장된 다른 실행자 실행 (SpringRunner 라는 실행자 사용)
@WebMvcTest(controllers = HelloController.class) // spring MVC에 집중하는 annotation, @Controller 관련 어노테이션 사용가능
public class HelloControllerTest {

    @Autowired  // spring bean injection
    private MockMvc mvc;    // web api 테스트할때 사용함, 스프링 mvc 테스트 시작점, 이 클래스를 통해 Http get,post등에 대한 api테스트 가능.

    @Test
    public void hello_return() throws Exception{
        String hello = "hello";

        mvc.perform(get("/hello"))  // MockMvc 를 통하여 /hello 주소로 get 요청을 함 -> 체이닝이 지원되어서 여러 검증 기능을 이어서 선언할 수 있음.
                .andExpect(status().isOk()) // mvc.perform 결과를 검증, http header status검증 (200,404,500 ) 상태 체크 -> 200이 아닌지 체크
                .andExpect(content().string(hello));    // mvc.perform 결과 검증, 본문에서 hello를 return하기때문에 이도 검증
    }

    @Test
    public void helloDto_return() throws Exception{
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                        .param("name",name)
                        .param("amount",String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))    // jsonPath 는 응답값을 필드별로 검증할 수 있게 해줌.
                .andExpect(jsonPath("$.amount", is(amount)));   // $를 기준으로 필드명을 명시합니다.
    }
}
