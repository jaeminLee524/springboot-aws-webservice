package com.jaemin.book.springbootawswebservice.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass // Entity들이 클래스를 상속할 경우 필드들을 컬럼으로 인식
@EntityListeners(AuditingEntityListener.class) //클래스에 Auditing 기능을 포함시킨다.
public abstract class BaseTimeEntity { // 직접 생성해서 사용하지 않기에 추상 클래스로 정의

    @CreatedDate // Entity가 생성되어 저장될 때 시간이 자동 저장된다.
    private LocalDateTime createdDate;

    @LastModifiedDate // 조회된 Entity의 값을 변경할 때 시간이 자동 저장된다.
    private LocalDateTime modifiedDate;
}
