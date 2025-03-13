package com.shop.subshop.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    private static final String SECRET_KEY = "mysecretkeymysecretkeymysecretkey"; // 256비트 이상 필요
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 10; // 10시간

    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    /**
     * JWT 토큰 생성 (기본: 역할 없이)
     */
    public String generateToken(String username) {
        // 역할 정보 없이 호출 시 빈 문자열로 처리
        return generateToken(username, "");
    }

    /**
     * JWT 토큰 생성 (사용자 이름과 역할 포함)
     */
    public String generateToken(String username, String role) {
        if (role == null) {
            role = "";
        }
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role) // 역할 정보를 claims에 추가
                .setIssuedAt(new Date()) // 생성 시간
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // 만료 시간
                .signWith(key, SignatureAlgorithm.HS256) // 서명 (보안 강화)
                .compact();
    }

    /**
     * JWT 토큰에서 사용자 이름 추출
     */
    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    /**
     * JWT 토큰 유효성 검증
     */
    public boolean validateToken(String token) {
        try {
            return getClaims(token).getExpiration().after(new Date()); // 만료 여부 확인
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * JWT 토큰에서 Claims(데이터) 가져오기
     */
    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * JWT 토큰을 HttpOnly 쿠키에 저장하는 예시 메서드
     */
    public void setJwtCookie(javax.servlet.http.HttpServletResponse response, String token) {
        javax.servlet.http.Cookie cookie = new javax.servlet.http.Cookie("accessToken", token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge((int)(EXPIRATION_TIME / 1000));
        // 운영 환경에서는 Secure 옵션과 SameSite 설정을 추가하세요.
        response.addCookie(cookie);
    }
}
