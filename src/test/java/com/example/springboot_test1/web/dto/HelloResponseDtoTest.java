package com.example.springboot_test1.web.dto;

/*
import 할때 static으로 해주게되면 new로 상속대신에 그냥 사용할 수 있다.
 */
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;   // assertj 라는 검증 라이브러리의 검증 메소드, 메소드 체이닝 지원이되어서 isEqualTo같은 메소드 연결해서 사용가능

public class HelloResponseDtoTest {

    @Test
    public void lombok_function_test(){
        //given
        String name ="test";
        int amount = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name,amount);

        //then
        assertThat(dto.getName()).isEqualTo(name);  // assertThat에 있는 값과 isEqualTo값을 비교해서 같은때에만 junit성공으로 된다.
        assertThat(dto.getAmount()).isEqualTo(amount);
    }
}
