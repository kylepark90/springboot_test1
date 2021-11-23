package com.example.springboot_test1.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass   // JPA Entity클래스들이 BaseTiemEntity을 상속할 경우 필드들 (createDate, modifiedDate)도 컬럼으로 인식하도록 한다.
@EntityListeners(AuditingEntityListener.class)  // BaseTimeEntity클래스에 Auditing 기능을 포함시킨다.
public class BaseTimeEntity {   // Entity의 상위 클래스가 되어 Entity들의 createDate, modifiedDate를 자동으로 관리하는 역활

    @CreatedDate    // Entity가 생성되어 저장될때 시간이 자동 저장이됨.
    private LocalDateTime createDate;

    @LastModifiedDate   // 조회한 Entity의 값을 변경할 때 시간이 자동 저장이됨.
    private LocalDateTime modifiedDate;
}
/*
    BaseTimeEntity 만 상송받으면 추가될 엔티티들에 등록일/수정일이 자동으로 들어가게됨.
 */