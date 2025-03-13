package com.shop.subshop.repository;

import com.shop.subshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // 이메일로 사용자 조회
    Optional<User> findByEmail(String email);

    // 이메일 중복 확인용 (true/false 반환)
    boolean existsByEmail(String email);

    // 필요에 따라 username, phone 등 다른 조회 메서드 추가 가능
}
