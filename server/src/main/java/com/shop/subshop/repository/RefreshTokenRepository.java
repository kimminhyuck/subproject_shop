package com.shop.subshop.repository;

import com.shop.subshop.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    // 토큰 문자열로 엔티티 조회
    Optional<RefreshToken> findByToken(String token);

    // 사용자 이메일로 여러 토큰을 관리할 수도 있음
    // 예: void deleteByEmail(String email);
}
