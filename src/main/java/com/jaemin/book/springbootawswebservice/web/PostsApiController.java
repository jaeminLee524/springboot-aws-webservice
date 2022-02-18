package com.jaemin.book.springbootawswebservice.web;

import com.jaemin.book.springbootawswebservice.domain.service.posts.PostsService;
import com.jaemin.book.springbootawswebservice.web.dto.PostsResponseDto;
import com.jaemin.book.springbootawswebservice.web.dto.PostsSaveRequestDto;
import com.jaemin.book.springbootawswebservice.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    /**
     * 게시글 저장 API
     */    
    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }

    /**
     * 게시글 수정 API 
     */
    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    /**
     * 게시글 삭제 API 
     */    
    @DeleteMapping("/api/vi/posts/{id}")
    public Long delete(@PathVariable Long id) {
        postsService.delete(id);

        return id;
    }
}
