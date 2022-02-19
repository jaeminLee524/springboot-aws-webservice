package com.jaemin.book.springbootawswebservice.config.auth;

import com.jaemin.book.springbootawswebservice.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Component
@Log4j2
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    private final HttpSession httpSession;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {

        // @LoginUser 여부
        boolean isLoginUserAnnotation = parameter.getParameterAnnotation(LoginUser.class) != null;

        // 파라미터 클래스 타입이 SessionUser.class인지 파악
        log.info(">>>>>>>>>>>parameter.getParameterType", parameter.getParameterType());
        boolean isUserClass = SessionUser.class.equals(parameter.getParameterType());

        return isLoginUserAnnotation && isUserClass;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        return httpSession.getAttribute("user");
    }
}
