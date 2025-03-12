package com.shop.subshop.security.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
        String email = oauthToken.getPrincipal().getAttribute("email"); // Google, Naver 등에서 가져오기

        // 세션에 사용자 정보를 저장 (예: 이메일)
        HttpSession session = request.getSession();
        session.setAttribute("userEmail", email);
        // 필요에 따라 다른 사용자 정보도 저장할 수 있음

        response.sendRedirect("http://localhost:3000"); // 프론트엔드 페이지로 리다이렉트
    }
}
