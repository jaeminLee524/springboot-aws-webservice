package com.jaemin.book.springbootawswebservice.config.auth;

import com.jaemin.book.springbootawswebservice.config.auth.dto.OAuthAttributes;
import com.jaemin.book.springbootawswebservice.config.auth.dto.SessionUser;
import com.jaemin.book.springbootawswebservice.domain.user.User;
import com.jaemin.book.springbootawswebservice.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

/**
 * 해당 클래스는 소셜 로그인 성공 후 후속  조치를 진행, 리소스 서버에서 사용자 정보를 갖고온 상태
 * CustomOAuth2UserService에선 가입 및 정보수정, 세션 저장 기능을 수행
 */

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        // registrationId: 현재 로그인 진행 중인 서비스를 구분하는 코드(구글, 네이버, 페북 등등)
        // ClientRegistration.class 에 소셜에대한 정보들이 담겨져 있음
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        // userNameAttributeName: OAuth2 로그인 진행 시 키가 되는 필드값
        // Google의 경우 코드를 지원, naver, kakao는 기본 지원X
        // Google default code: 'sub'
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();


        // OAuthAttributes: OAuth2User의 attribute를 담는 클래스
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        // 사용자의 정보가 업데이트 되었을 때 대비하여 update 기능을 추가
        User user = saveOrUpdate(attributes);

        // SessionUser: 세션에 사용자 정보를 저장하기 위한 Dto
        httpSession.setAttribute("user",  new SessionUser(user));


        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                        attributes.getAttributes(),
                        attributes.getNameAttributeKey());
    }

    private User saveOrUpdate(OAuthAttributes attributes) {
        return userRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName(), attributes.getPicture())
                ).orElse(attributes.toEntity());
    }
}
