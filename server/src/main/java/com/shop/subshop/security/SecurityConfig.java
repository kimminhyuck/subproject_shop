package com.shop.subshop.security;

import com.shop.subshop.security.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    // JwtUtil: JWT 생성, 검증, 쿠키에 JWT 저장 등을 담당하는 유틸리티 클래스
    private final JwtUtil jwtUtil;
    // UserDetailsService: 스프링 시큐리티에서 사용자 정보를 불러오기 위한 서비스
    private final UserDetailsService userDetailsService;

    /**
     * SecurityFilterChain 설정
     * 
     * - CORS 설정을 적용하여 React 클라이언트(http://localhost:3000)에서의 요청을 허용합니다.
     * - CSRF 보호는 JWT를 사용하기 때문에 비활성화합니다.
     * - 세션 관리는 STATELESS로 설정하여, 서버에 상태를 저장하지 않고 JWT를 이용해 인증을 수행합니다.
     * - 특정 엔드포인트(루트, /public/**, /api/auth/**)는 인증 없이 접근을 허용하고,
     *   /api/admin/**는 ADMIN 역할만 접근하도록 제한합니다.
     * - 나머지 요청은 모두 인증이 필요합니다.
     * - JwtAuthenticationFilter를 UsernamePasswordAuthenticationFilter 이전에 추가하여
     *   JWT를 통한 인증 처리를 수행합니다.
     *
     * @param http HttpSecurity 객체
     * @return 구성된 SecurityFilterChain
     * @throws Exception 설정 과정에서 예외 발생 시 던짐
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // CORS 설정 적용: 아래 corsConfigurationSource() 메서드에서 설정한 내용 사용
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            // CSRF 보호 비활성화 (JWT 사용 시 CSRF는 필요하지 않음)
            .csrf(csrf -> csrf.disable())
            // 세션 관리를 STATELESS로 설정하여 JWT 기반 인증 사용
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            // 접근 제어 설정: 특정 URL은 인증 없이, 나머지는 인증 필요
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/public/**", "/api/auth/**").permitAll()
                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            // JWT 인증 필터를 UsernamePasswordAuthenticationFilter 이전에 추가
            .addFilterBefore(new JwtAuthenticationFilter(jwtUtil, userDetailsService), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /**
     * CORS 설정 소스
     * 
     * - React 클라이언트의 도메인(http://localhost:3000)에서의 요청을 허용합니다.
     * - GET, POST, PUT, DELETE, OPTIONS 메서드를 허용합니다.
     * - 쿠키와 인증 헤더가 함께 전송될 수 있도록 withCredentials를 true로 설정합니다.
     * - Authorization과 Content-Type 헤더를 허용합니다.
     *
     * @return CorsConfigurationSource
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // React 클라이언트 도메인 허용
        configuration.setAllowedOrigins(List.of("http://localhost:3000"));
        // 허용 HTTP 메서드 설정
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        // 쿠키 등의 자격 증명을 허용
        configuration.setAllowCredentials(true);
        // 허용할 HTTP 헤더 설정
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // 모든 경로에 대해 위 CORS 설정 적용
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    /**
     * BCryptPasswordEncoder를 사용한 PasswordEncoder Bean
     * 
     * - 비밀번호 암호화를 위한 Encoder입니다.
     *
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * AuthenticationManager Bean 구성
     * 
     * - DaoAuthenticationProvider를 사용하여 UserDetailsService와 PasswordEncoder를 설정합니다.
     * - ProviderManager를 통해 AuthenticationManager를 생성합니다.
     *
     * @param authenticationConfiguration 인증 설정 객체
     * @return AuthenticationManager
     * @throws Exception 설정 중 예외 발생 시 던짐
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(provider);
    }
}
