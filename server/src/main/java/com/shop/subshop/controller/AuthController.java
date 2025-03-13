package com.shop.subshop.controller;

import com.shop.subshop.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * AuthController: 인증/인가와 관련된 회원 기능을 처리하는 컨트롤러
 * (실제 비즈니스 로직은 AuthService에서 수행)
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /** =======================================
     * 1) 회원가입 (POST /auth/register)
     * =======================================
     * 클라이언트에서 전달받은 JSON -> Map<String, String>
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Map<String, String> request) {
        return authService.register(request);
    }

    /** =======================================
     * 2) 로그인 (POST /auth/login)
     * - JWT 발급 및 HttpOnly 쿠키 저장
     * =======================================
     * request: 로그인 정보 (이메일, 비밀번호 등)
     * response: 서블릿 응답 객체 (자동 주입, @RequestBody 아님)
     */
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(
            @RequestBody Map<String, String> request,
            HttpServletResponse response
    ) {
        return authService.login(request, response);
    }

    /** =======================================
     * 3) 로그아웃 (POST /auth/logout)
     * - 쿠키 삭제
     * =======================================
     */
    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser(HttpServletResponse response) {
        return authService.logout(response);
    }

    /** =======================================
     * 4) 프로필 조회 (GET /auth/profile)
     * =======================================
     * request: 서블릿 요청 객체 (쿠키나 헤더에서 JWT 추출)
     */
    @GetMapping("/profile")
    public ResponseEntity<?> getUserProfile(HttpServletRequest request) {
        return authService.getProfile(request);
    }

    /** =======================================
     * 5) 중복 확인 (POST /auth/check-duplicate)
     * =======================================
     */
    @PostMapping("/check-duplicate")
    public ResponseEntity<?> checkDuplicate(@RequestBody Map<String, String> data) {
        return authService.checkDuplicate(data);
    }

    /** =======================================
     * 6) 프로필 수정 (PUT /auth/profile/update)
     * =======================================
     * request: JWT 추출, userData: 수정할 프로필 정보
     */
    @PutMapping("/profile/update")
    public ResponseEntity<?> updateProfile(
            HttpServletRequest request,
            @RequestBody Map<String, String> userData
    ) {
        return authService.updateProfile(request, userData);
    }

    /** =======================================
     * 7) 비밀번호 변경 (PUT /auth/change-password)
     * =======================================
     * request: JWT 추출, passwordData: {"newPassword":"..."}
     */
    @PutMapping("/change-password")
    public ResponseEntity<?> changePassword(
            HttpServletRequest request,
            @RequestBody Map<String, String> passwordData
    ) {
        return authService.changePassword(request, passwordData);
    }

    /** =======================================
     * 8) 비밀번호 찾기 (POST /auth/forgot-password)
     * =======================================
     */
    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody Map<String, String> data) {
        return authService.forgotPassword(data);
    }

    /** =======================================
     * 9) 비밀번호 재설정 (POST /auth/reset-password)
     * =======================================
     */
    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody Map<String, String> data) {
        return authService.resetPassword(data);
    }

    /** =======================================
     * 10) 리프레시 토큰 (POST /auth/refresh-token)
     * - 액세스 토큰 만료 시 재발급 (예시)
     * =======================================
     */
    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        return authService.refreshToken(request, response);
    }

    /** =======================================
     * 11) 아이디 찾기 (POST /auth/find-userid)
     * =======================================
     */
    @PostMapping("/find-userid")
    public ResponseEntity<?> findUserId(@RequestBody Map<String, String> data) {
        return authService.findUserId(data);
    }

    /** =======================================
     * 12) 인증 코드 확인 (POST /auth/verify-code)
     * =======================================
     */
    @PostMapping("/verify-code")
    public ResponseEntity<?> verifyCode(@RequestBody Map<String, String> data) {
        return authService.verifyCode(data);
    }
}
