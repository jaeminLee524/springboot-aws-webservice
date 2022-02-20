package com.jaemin.book.springbootawswebservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@EnableJpaAuditing //JPA Auditing 활성화, JpaConfig를 생성 후 분리
@SpringBootApplication
public class SpringbootAwsWebserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootAwsWebserviceApplication.class, args);
    }

}
