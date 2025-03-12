package com.shop.subshop.security.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.shop.subshop.security.JwtUtil;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {
    private final JwtUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
        String username = oauthToken.getPrincipal().getAttribute("email"); // Google, Naver 등에서 가져오기

        String jwt = jwtUtil.generateToken(username);
        jwtUtil.setJwtCookie(response, jwt);

        response.sendRedirect("http://localhost:3000"); // 프론트엔드 페이지로 리다이렉트
    }
}
