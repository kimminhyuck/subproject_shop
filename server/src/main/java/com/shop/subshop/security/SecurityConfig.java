package com.shop.subshop.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtUtil jwtUtil;
    // private final OAuth2SuccessHandler oAuth2SuccessHandler; // 소셜 로그인 핸들러 주석 처리

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())  // CSRF 비활성화 (JWT는 CSRF 방어가 필요 없음)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/","/public/**","/auth/**").permitAll() // 인증 없이 접근 가능
                .anyRequest().authenticated() // 나머지 요청은 인증 필요
            );

            // .oauth2Login(oauth2 -> oauth2
            //     .successHandler(oAuth2SuccessHandler) //  소셜 로그인 관련 코드 주석 처리
            // );

        return http.build();
    }
}
