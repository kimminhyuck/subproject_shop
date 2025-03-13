package com.shop.subshop.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

/**
 * RefreshToken 엔티티
 * - 리프레시 토큰 문자열(token)과 사용자 이메일(또는 userId) 등을 저장하여 관리
 * - 만료 시간(expiryDate) 등을 통해 토큰의 유효기간을 설정/확인 가능
 */
@Entity
@Table(name = "refresh_tokens")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;               // 고유 ID

    @Column(nullable = false, unique = true)
    private String token;          // 실제 리프레시 토큰 문자열

    @Column(nullable = false)
    private String email;          // 토큰 소유자를 식별하기 위한 필드 (또는 userId 사용)

    @Column(nullable = false)
    private Instant expiryDate;    // 토큰 만료 시각
}
