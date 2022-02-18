package com.jaemin.book.springbootawswebservice.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Enumerated;

@Getter
@RequiredArgsConstructor
public enum Role {

    // spring security의 권한 코드에는 항상 "ROLE_"가 접두사로 붙어있어야 함
    GUEST("ROLE_GUEST", "손님"),
    USER("ROLE_USER", "일반 사용자");

    private final String key;
    private final String title;
}
