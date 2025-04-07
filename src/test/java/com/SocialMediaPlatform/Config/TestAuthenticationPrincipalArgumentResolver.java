package com.SocialMediaPlatform.Config;

import org.springframework.core.MethodParameter;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import com.SocialMediaPlatform.Domain.User;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class TestAuthenticationPrincipalArgumentResolver implements HandlerMethodArgumentResolver {
    private final String userID;

    public TestAuthenticationPrincipalArgumentResolver(String userID) {
        this.userID = userID;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(AuthenticationPrincipal.class) != null;
    }

    @Override
    public User resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {

        return User.builder()
                .id(userID)
                .email("test@test.com")
                .build();
    }
}