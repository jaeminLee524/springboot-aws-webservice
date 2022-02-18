package com.jaemin.book.springbootawswebservice.domain.service.posts;

import com.jaemin.book.springbootawswebservice.domain.posts.Posts;
import com.jaemin.book.springbootawswebservice.domain.posts.PostsRepository;
import com.jaemin.book.springbootawswebservice.web.dto.PostsListResponseDto;
import com.jaemin.book.springbootawswebservice.web.dto.PostsResponseDto;
import com.jaemin.book.springbootawswebservice.web.dto.PostsSaveRequestDto;
import com.jaemin.book.springbootawswebservice.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.hibernate.engine.internal.Collections;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    /**
     * 게시글 저장
     */
    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        System.out.println("====start=====");
        Long id = postsRepository.save(requestDto.toEntity()).getId();
        System.out.println("====end=====");
        System.out.println("id = " + id);
        return id;
    }

    /**
     * 게시글 수정 
     */
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    /**
     * 게시글 단건 조회
     */
    public PostsResponseDto findById(Long id) {
        Posts findPost = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(findPost);
    }

    /**
     * 게시글 목록 조회
     */
    @Transactional
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findALlDesc().stream()
                .map(posts -> new PostsListResponseDto(posts))
                .collect(Collectors.toList());
    }

    /**
     * 게시글 삭제
     */
    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new
                IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        postsRepository.delete(posts);
    }
}
