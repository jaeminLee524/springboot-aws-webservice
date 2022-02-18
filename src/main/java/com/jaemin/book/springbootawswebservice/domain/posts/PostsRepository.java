package com.jaemin.book.springbootawswebservice.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//Entity클래스와 기본 Repository는 함께 움직여야 하기에 도메인 패키지에서 관리
public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Query("SELECT p FROM Posts p ORDER BY p.id desc")
    List<Posts> findALlDesc();
}
