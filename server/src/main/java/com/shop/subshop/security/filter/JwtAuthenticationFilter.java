package com.shop.subshop.security.filter;

import com.shop.subshop.security.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * JwtAuthenticationFilter는 모든 HTTP 요청을 가로채어 JWT 토큰이 포함되어 있는지 확인합니다.
 * 토큰은 Authorization 헤더 또는 "accessToken" 쿠키에서 추출하며, 유효한 토큰이 발견되면
 * 해당 사용자 정보를 로드하고 Spring Security의 SecurityContext에 인증 객체를 설정합니다.
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    /**
     * 생성자
     *
     * @param jwtUtil             JWT 생성 및 검증을 위한 유틸리티
     * @param userDetailsService  사용자 정보를 로드하기 위한 서비스
     */
    public JwtAuthenticationFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    /**
     * 모든 HTTP 요청마다 호출되어 JWT를 검증하고, 사용자 인증을 처리합니다.
     *
     * @param request     HttpServletRequest 객체
     * @param response    HttpServletResponse 객체
     * @param filterChain FilterChain 객체
     * @throws ServletException 예외 발생 시 던짐
     * @throws IOException      I/O 예외 발생 시 던짐
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = null;
        String refreshToken = null;

        // 1. Authorization 헤더에서 토큰 추출 ("Bearer " 접두어 사용)
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
        } else {
            // 2. 헤더에 토큰이 없으면, 쿠키에서 "accessToken" 이름의 토큰 추출
            if (request.getCookies() != null) {
                for (Cookie cookie : request.getCookies()) {
                    if ("accessToken".equals(cookie.getName())) {
                        token = cookie.getValue();
                        break;
                    } else if ("refreshToken".equals(cookie.getName())) {
                        refreshToken = cookie.getValue();
                    }
                }
            }
        }

        // 3. 액세스 토큰이 만료된 경우 리프레시 토큰을 사용하여 새 액세스 토큰을 발급
        if (token != null && !jwtUtil.validateToken(token)) {
            if (refreshToken != null && jwtUtil.validateToken(refreshToken)) {
                String username = jwtUtil.extractUsername(refreshToken);
                // 새 액세스 토큰 발급
                String newAccessToken = jwtUtil.generateToken(username);

                // 새로운 액세스 토큰을 HttpOnly 쿠키로 저장
                jwtUtil.setJwtCookie(response, newAccessToken);

                // 새 토큰을 헤더에 포함시켜서 요청을 계속 처리하도록 함
                response.setHeader("Authorization", "Bearer " + newAccessToken);
                token = newAccessToken;
            }
        }

        // 4. 유효한 토큰이 있으면 SecurityContext에 인증 설정
        if (token != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // 유효한 액세스 토큰이라면 인증 처리
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 이름 추출
                String username = jwtUtil.extractUsername(token);
                // UserDetailsService를 통해 사용자 정보 로드
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                // 인증 토큰 생성 (비밀번호는 null, 권한은 userDetails에서 가져옴)
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                // 요청 정보 추가 (IP, 세션 ID 등)
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // Spring Security의 SecurityContext에 인증 객체 설정
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        // 5. 다음 필터로 요청 전달
        filterChain.doFilter(request, response);
    }
}
