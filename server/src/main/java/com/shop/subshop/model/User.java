package com.shop.subshop.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "users")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 고유 ID

    @Column(nullable = false, unique = true)
    private String username; // 로그인 ID (이메일과 별개)

    @Column(nullable = false, unique = true)
    private String email; // 이메일

    @Column(nullable = false)
    private String password; // 암호화된 비밀번호

    @Column(nullable = false)
    private String name; // 이름

    @Column(nullable = true)
    private String gender; // 성별 (M, F, OTHER)

    @Column(nullable = true)
    private String phone; // 전화번호

    @Column(nullable = true)
    private String address; // 카카오 도로명 주소 API 주소

    @Column(nullable = true)
    private String detailAddress; // 상세 주소 (도로명 주소 + 추가 정보)

    @Column(nullable = true)
    private String zipcode; // 우편번호

    @Column(nullable = false)
    private int mileage = 0; // 기본값 0 (마일리지 포인트)

    @Column(columnDefinition = "TEXT", nullable = true)
    private String couponBox; // JSON 형태로 보관 가능

    @Column(nullable = false)
    private String role = "ROLE_USER"; // 기본값 ROLE_USER
}
