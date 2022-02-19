package com.jaemin.book.springbootawswebservice.web;

import com.jaemin.book.springbootawswebservice.config.auth.LoginUser;
import com.jaemin.book.springbootawswebservice.config.auth.dto.SessionUser;
import com.jaemin.book.springbootawswebservice.domain.service.posts.PostsService;
import com.jaemin.book.springbootawswebservice.web.dto.PostsListResponseDto;
import com.jaemin.book.springbootawswebservice.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    /**
     * 게시글 목록 화면
     * return index.mustache
     */
    @GetMapping("/")
    public String index(@LoginUser SessionUser sessionUser, Model model) {
        model.addAttribute("posts", postsService.findAllDesc());

        // CustomOAuth2UserService에서 로그인 성공 시 세션에 저장하기로 함, extract userName from session
        // annotation으로 생성
        // SessionUser findUser = (SessionUser) httpSession.getAttribute("user");

        if( sessionUser != null ){
            model.addAttribute("userName", sessionUser.getName());
        }

        return "index";
    }

    /**
     * 게시글 등록 화면
     * return posts-save.mustache
     */
    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    /**
     * 게시글 수정 화면
     * call by index.mustache
     * return posts-update.mustache
     */
    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto findPost = postsService.findById(id);

        model.addAttribute("post", findPost);

        return "posts-update";
    }

}
