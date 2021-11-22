package com.example.springboot_test1.domain.posts;

// lombok annotation
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// JPA annotation
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// Entity class 에서는 절대 Setter메소드를 만들지 않다 -> setter 대신에 생성자를 통해 최종값을 채운 후 DB에 삽입해야되며 변경시에는 해당 이벤트에 맞는 public 메소드를 호출하여 변경함.
// 하지만 @Builder annotation를 통해 제공되는 빌더 클래스 역시 값을 채워주는 역활을 할 수 있다.
// 중요 annotation 일수록 class와 가깝게 두는것이 좋다, 코틀린같은 경우는 lombok이 필요없다.
@Getter
@NoArgsConstructor  // 기본생성자 자동 추가 i.e) public Posts()
@Entity     // 테이블과 링크될 클래스임을 나타냄, 클래스의 카멜케이스 이름을 언더스코어 네이밍(_)으로 테이블 이름 매칭 ie) SalesManager.java -> sales_manager table
public class Posts { // 실제 DB 테이블과 매칭될 클래스 Entity class라고 불린다. -> DB데이터 작업이 필요시 Entity class 수정을 통해 작업진행.

    @Id     // 해당 테이블 pk필드를 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // pk의 생성 규칙을 나타냄 GenerationType.IDENTITY -> auto_increment 기능
    private long id;    // 웬만하면 Entity의 PK는 Long타입의 Auto_increment를 사용하는것이 좋음 (mysql 은 bigint)

    @Column(length = 500, nullable = false)     // 선언 안하더라도 자동으로 선언되나, 기본값 외에 추가로 변경이 필요할때에 사용 (컬럼 사이즈 변경, text(content) 사용등)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder        // 빌더 패턴 클래스 생성, 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함됨. => 빌더 패턴을 사용하므로 파라메터 갯수에 상관없이 사용할 수 있음.
    public Posts(String title, String content, String author){  // Builder 로 생성자대신에 값을 채워 줄 수 있다.
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
    /*
    빌더 패턴
    만일 어떤 메소드에 set으로 담아야되는값이 3개가 있었는데 4개로 늘었다고 하면 기존과 같이
    User user = new User("개발자",28,180,150) -> 맨끝에 150을 모든 메소드를 추가해주어야겠지만
    빌더패턴을 사용하게 되면 기존 아래와 같이 3개로만 호출해도 더미값이 들어가므로 에러를 발생 시키지 않는다.
    User user = User.builder()
        .name("개발자")
        .age(28)
        .iq(150).build()

     */
}
