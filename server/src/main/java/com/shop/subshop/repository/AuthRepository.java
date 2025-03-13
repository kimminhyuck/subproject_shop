package com.shop.subshop.repository;

import com.shop.subshop.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<RefreshToken, Long> {
    
    // 토큰 문자열로 엔티티 조회
    Optional<RefreshToken> findByToken(String token);

    // 이메일로 여러 개의 리프레시 토큰을 찾거나 삭제할 수도 있음
    void deleteByToken(String token);
}
