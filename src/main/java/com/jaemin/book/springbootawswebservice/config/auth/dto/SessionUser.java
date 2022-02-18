package com.jaemin.book.springbootawswebservice.config.auth.dto;

import com.jaemin.book.springbootawswebservice.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

/**
 * 인증된 사용자 정보만 필요 
 * Serializable interface를 구현하면, JVM에서 해당 객체는 저장하거나 다른 서버로 전송 가능하게 함
 */
@Getter
public class SessionUser implements Serializable {

    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
