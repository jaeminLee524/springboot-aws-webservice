package com.jaemin.book.springbootawswebservice.domain.posts;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
//@Transactional(readOnly = true) After method를 생략하고 @Transactional 을 이용해 테스트 가능
public class PostsRepositoryTest {

    @Autowired
    private PostsRepository postsRepository;

    @After
    public void cleanUp() {
        postsRepository.deleteAll();
    }

//    @Transactional
    @Test
    public void 게시글저장_불러오기() {

        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        //save data using the builder pattern
        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("fkdlem524@naver.com")
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        Assertions.assertThat(posts.getTitle()).isEqualTo(title);
        Assertions.assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록() throws Exception {

        //given
        LocalDateTime now = LocalDateTime.of(2022, 2, 17, 0, 0, 0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts findPost = postsList.get(0);

        System.out.println(">>>>>>>>>>>> createDate= " + findPost.getCreatedDate() + "modifiedDate= " + findPost.getModifiedDate());

        Assertions.assertThat(findPost.getCreatedDate()).isAfter(now);
        Assertions.assertThat(findPost.getModifiedDate()).isAfter(now);

    }

}