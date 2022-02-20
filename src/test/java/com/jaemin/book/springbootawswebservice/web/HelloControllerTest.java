package com.jaemin.book.springbootawswebservice.web;

import com.jaemin.book.springbootawswebservice.config.auth.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
//@Repository, @Service, @Component는 스캔 대상X, SecurityConfig.class의 CustomOAuth2UserService.class을 스캔할 수 없음
@WebMvcTest(controllers = HelloController.class,
        excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes= SecurityConfig.class)

        }
)
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @WithMockUser(roles = "USER")
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(
                get("/hello"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().string(hello));
    }

    @Test
    @WithMockUser(roles = "USER")
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                        get("/hello/dto")
                                .param("name", name)
                                .param("amount", String.valueOf(amount))) //param : String 값만 허용 => 숫자/날짜 등의 데이터를 String으로 변환해야됨
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name))) //$를 기준으로 필드명을 명시
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}