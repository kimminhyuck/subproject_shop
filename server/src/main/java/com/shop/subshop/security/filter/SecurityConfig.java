package com.shop.subshop.security.filter;

import com.shop.subshop.security.JwtUtil;
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
// (필요시) 아래와 같이 메서드 보안을 활성화할 수 있음
// import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
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
// 만약 메서드 수준의 보안(@PreAuthorize 등)을 사용하려면 아래 어노테이션을 활성화합니다.
// @EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

    // JwtUtil: JWT 생성, 검증 및 쿠키 설정 등의 기능을 제공하는 유틸리티 클래스
    private final JwtUtil jwtUtil;
    // UserDetailsService: 스프링 시큐리티에서 사용자 정보를 로드하기 위한 커스텀 서비스
    private final UserDetailsService userDetailsService;

    /**
     * SecurityFilterChain Bean 구성
     *
     * <ul>
     *     <li>CORS 설정: React 클라이언트(http://localhost:3000)에서의 요청을 허용합니다.</li>
     *     <li>CSRF 보호 비활성화: JWT와 쿠키를 통한 인증을 사용하므로 CSRF 보호는 필요하지 않습니다.</li>
     *     <li>세션 관리: STATELESS 방식 사용 (서버에 세션 저장 없이 JWT 기반 인증 사용)</li>
     *     <li>URL 접근 제어:
     *         <ul>
     *             <li>루트, /public/**, /api/auth/** 경로는 인증 없이 접근 허용</li>
     *             <li>/api/admin/** 경로는 ADMIN 역할이 있는 사용자만 접근 가능</li>
     *             <li>그 외 모든 요청은 인증 필요</li>
     *         </ul>
     *     </li>
     *     <li>JwtAuthenticationFilter 추가: HTTP 요청에서 Authorization 헤더 또는 쿠키에 저장된 JWT를 추출하여 인증을 처리합니다.</li>
     * </ul>
     *
     * @param http HttpSecurity 객체
     * @return 구성된 SecurityFilterChain
     * @throws Exception 설정 과정에서 발생한 예외
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // CORS 설정: 아래 corsConfigurationSource() 메서드에서 구성한 내용을 사용
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            // CSRF 보호 비활성화: JWT 사용 시 CSRF는 필요하지 않습니다.
            .csrf(csrf -> csrf.disable())
            // 세션 관리: JWT 기반 인증이므로 서버에 상태를 저장하지 않음
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            // URL 접근 제어 설정
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/public/**", "/api/auth/**").permitAll()   // 인증 없이 접근 가능
                .requestMatchers("/api/admin/**").hasRole("ADMIN")                  // ADMIN 역할 사용자만 접근
                .anyRequest().authenticated()                                      // 그 외 요청은 인증 필요
            )
            // JWT 인증 필터 추가: UsernamePasswordAuthenticationFilter보다 앞서 JWT를 확인하여 인증 처리
            .addFilterBefore(new JwtAuthenticationFilter(jwtUtil, userDetailsService), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /**
     * CorsConfigurationSource Bean 구성
     *
     * React 클라이언트(http://localhost:3000)에서 오는 요청을 허용하기 위해,
     * 허용 오리진, HTTP 메서드, 허용 헤더, 자격 증명(쿠키) 등을 설정합니다.
     *
     * @return CorsConfigurationSource 객체
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // 허용할 오리진: React 클라이언트 URL
        configuration.setAllowedOrigins(List.of("http://localhost:3000"));
        // 허용할 HTTP 메서드들
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        // 쿠키, 인증 헤더 등의 자격 증명을 허용
        configuration.setAllowCredentials(true);
        // 허용할 헤더들
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // 모든 경로에 대해 위의 CORS 설정 적용
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    /**
     * PasswordEncoder Bean 구성
     *
     * BCryptPasswordEncoder를 사용하여 사용자 비밀번호를 암호화합니다.
     *
     * @return PasswordEncoder 인스턴스
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * AuthenticationManager Bean 구성
     *
     * DaoAuthenticationProvider를 사용하여 UserDetailsService와 PasswordEncoder를 설정한 후,
     * ProviderManager를 통해 AuthenticationManager를 생성합니다.
     *
     * @param authenticationConfiguration AuthenticationConfiguration 객체
     * @return AuthenticationManager 인스턴스
     * @throws Exception 설정 과정에서 발생한 예외
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(provider);
    }
}
