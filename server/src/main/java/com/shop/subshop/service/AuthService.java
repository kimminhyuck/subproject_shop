package com.shop.subshop.service;

import com.shop.subshop.model.RefreshToken;
import com.shop.subshop.model.User;
import com.shop.subshop.repository.RefreshTokenRepository;
import com.shop.subshop.repository.UserRepository;
import com.shop.subshop.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository,
                       RefreshTokenRepository refreshTokenRepository,
                       PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.refreshTokenRepository = refreshTokenRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    /**
     * 회원가입 처리
     * 클라이언트에서 전달받은 정보를 기반으로 새 사용자를 등록합니다.
     */
    public ResponseEntity<?> register(Map<String, String> request) {
        String username = request.get("username");
        String email = request.get("email");
        String password = request.get("password");
        String name = request.get("name");
        String gender = request.get("gender");
        String phone = request.get("phone");
        String address = request.get("address");
        String detailAddress = request.get("detailAddress");
        String zipcode = request.get("zipcode");

        // 이미 가입된 이메일인지 확인
        if (userRepository.findByEmail(email).isPresent()) {
            return ResponseEntity.badRequest().body("이미 가입된 이메일입니다.");
        }

        // 새 User 엔티티 생성 (비밀번호는 BCrypt로 암호화)
        User newUser = User.builder()
                .username(username)
                .email(email)
                .password(passwordEncoder.encode(password))
                .name(name)
                .gender(gender)
                .phone(phone)
                .address(address)
                .detailAddress(detailAddress)
                .zipcode(zipcode)
                .role("ROLE_USER")
                .build();

        userRepository.save(newUser);
        return ResponseEntity.ok("회원가입 성공");
    }

    /**
     * 로그인 처리
     * 이메일과 비밀번호를 검증하고, JWT를 생성하여 HttpOnly 쿠키에 저장합니다.
     */
    public ResponseEntity<?> login(Map<String, String> request, HttpServletResponse response) {
        String email = request.get("email");
        String password = request.get("password");
    
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                // JWT 생성: 사용자 이메일과 역할 포함
                String accessToken = jwtUtil.generateToken(user.getEmail(), user.getRole());
                String refreshToken = jwtUtil.generateRefreshToken(user.getEmail());
    
                // 리프레시 토큰을 DB에 저장
                RefreshToken refreshTokenEntity = new RefreshToken();
                refreshTokenEntity.setToken(refreshToken);
                refreshTokenEntity.setEmail(user.getEmail());
                refreshTokenEntity.setExpiryDate(jwtUtil.getRefreshTokenExpirationDate()); // 만료일 추가
                refreshTokenRepository.save(refreshTokenEntity);  // DB에 저장 확인
    
                // JWT와 리프레시 토큰을 HttpOnly 쿠키로 저장
                jwtUtil.setJwtCookie(response, accessToken);
                jwtUtil.setRefreshTokenCookie(response, refreshToken);
    
                return ResponseEntity.ok(Map.of("accessToken", accessToken, "refreshToken", refreshToken));
            }
        }
        return ResponseEntity.status(401).body("이메일 또는 비밀번호가 올바르지 않습니다.");
    }
    /**
     * 로그아웃 처리
     * HttpServletResponse를 이용하여 accessToken 쿠키를 만료시킵니다.
     */
    public ResponseEntity<?> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("accessToken", null);
        cookie.setMaxAge(0); // 쿠키 만료
        cookie.setPath("/"); // 전체 경로에서 유효
        response.addCookie(cookie); // 쿠키 추가
        return ResponseEntity.ok("로그아웃 성공");
    }

    /**
     * 프로필 조회 처리
     * 요청의 헤더나 쿠키에서 JWT를 추출하여 사용자 정보를 반환합니다.
     */
    public ResponseEntity<?> getProfile(HttpServletRequest request) {
        String token = extractToken(request);
        if (token == null) {
            return ResponseEntity.status(401).body("인증 토큰이 필요합니다.");
        }
        if (!jwtUtil.validateToken(token)) {
            return ResponseEntity.status(401).body("유효하지 않은 토큰입니다.");
        }
        String email = jwtUtil.extractUsername(token);
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.status(404).body("사용자를 찾을 수 없습니다.");
    }

    /**
     * 중복 확인 (예: 이메일 중복 확인)
     */
    public ResponseEntity<?> checkDuplicate(Map<String, String> data) {
        String email = data.get("email");
        if (email == null || email.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("이메일이 필요합니다.");
        }
        boolean exists = userRepository.findByEmail(email).isPresent();
        if (exists) {
            return ResponseEntity.badRequest().body("중복된 이메일입니다.");
        }
        return ResponseEntity.ok("사용 가능한 이메일입니다.");
    }

    /**
     * 프로필 수정 처리
     * JWT로 인증된 사용자의 정보를 수정합니다.
     */
    public ResponseEntity<?> updateProfile(HttpServletRequest request, Map<String, String> userData) {
        String token = extractToken(request);
        if (token == null || !jwtUtil.validateToken(token)) {
            return ResponseEntity.status(401).body("인증 토큰이 유효하지 않습니다.");
        }
        String email = jwtUtil.extractUsername(token);
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(404).body("사용자를 찾을 수 없습니다.");
        }
        User user = userOptional.get();
        if (userData.containsKey("name")) user.setName(userData.get("name"));
        if (userData.containsKey("phone")) user.setPhone(userData.get("phone"));
        if (userData.containsKey("address")) user.setAddress(userData.get("address"));
        if (userData.containsKey("detailAddress")) user.setDetailAddress(userData.get("detailAddress"));
        if (userData.containsKey("zipcode")) user.setZipcode(userData.get("zipcode"));
        userRepository.save(user);
        return ResponseEntity.ok("프로필 수정 성공");
    }

    /**
     * 비밀번호 변경 처리
     * JWT로 인증된 사용자의 비밀번호를 새 비밀번호로 변경합니다.
     */
    public ResponseEntity<?> changePassword(HttpServletRequest request, Map<String, String> passwordData) {
        String token = extractToken(request);
        if (token == null || !jwtUtil.validateToken(token)) {
            return ResponseEntity.status(401).body("인증 토큰이 유효하지 않습니다.");
        }
        String email = jwtUtil.extractUsername(token);
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(404).body("사용자를 찾을 수 없습니다.");
        }
        String newPassword = passwordData.get("newPassword");
        if (newPassword == null || newPassword.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("새 비밀번호가 필요합니다.");
        }
        User user = userOptional.get();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        return ResponseEntity.ok("비밀번호 변경 성공");
    }

    /**
     * 비밀번호 찾기 처리
     * 이메일을 기반으로 인증 메일(예시)을 발송하는 로직
     */
    public ResponseEntity<?> forgotPassword(Map<String, String> data) {
        String email = data.get("email");
        if (email == null || email.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("이메일이 필요합니다.");
        }
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("해당 이메일 사용자가 없습니다.");
        }
        // 실제 서비스에서는 OTP 생성 및 이메일 전송 로직 구현 필요
        return ResponseEntity.ok("비밀번호 찾기 인증 메일 발송 (예시)");
    }

    /**
     * 비밀번호 재설정 처리
     * 인증 코드 확인 후 새로운 비밀번호로 변경하는 로직
     */
    public ResponseEntity<?> resetPassword(Map<String, String> data) {
        String email = data.get("email");
        String newPassword = data.get("newPassword");
        // 실제로는 인증 코드 검증 로직이 필요합니다.
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("해당 이메일 사용자가 없습니다.");
        }
        if (newPassword == null || newPassword.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("새 비밀번호가 필요합니다.");
        }
        User user = userOptional.get();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        return ResponseEntity.ok("비밀번호 재설정 성공");
    }

    /**
     * 리프레시 토큰 처리
     * 액세스 토큰이 만료되었을 경우 새로운 액세스 토큰을 발급합니다.
     */
    public ResponseEntity<?> refreshToken(HttpServletRequest request, HttpServletResponse response) {
        // 여기서는 간단한 예시로 accessToken 재발급을 처리합니다.
        String token = extractToken(request);
        if (token == null || !jwtUtil.validateToken(token)) {
            return ResponseEntity.status(401).body("리프레시 토큰이 유효하지 않습니다.");
        }
        String email = jwtUtil.extractUsername(token);
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(404).body("사용자를 찾을 수 없습니다.");
        }
        User user = userOptional.get();
        // 새 액세스 토큰 발급 (사용자 이메일과 역할 포함)
        String newAccessToken = jwtUtil.generateToken(user.getEmail(), user.getRole());
        jwtUtil.setJwtCookie(response, newAccessToken);
        return ResponseEntity.ok(Map.of("accessToken", newAccessToken));
    }

    /**
     * 아이디 찾기 처리
     * 이메일을 기반으로 사용자 아이디(username)을 반환합니다.
     */
    public ResponseEntity<?> findUserId(Map<String, String> data) {
        String email = data.get("email");
        if (email == null || email.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("이메일이 필요합니다.");
        }
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("해당 이메일 사용자가 없습니다.");
        }
        User user = userOptional.get();
        return ResponseEntity.ok(Map.of("username", user.getUsername()));
    }

    /**
     * 인증 코드 확인 처리
     * 비밀번호 찾기 등에서 발송한 인증 코드를 검증합니다. (예시)
     */
    public ResponseEntity<?> verifyCode(Map<String, String> data) {
        String email = data.get("email");
        String code = data.get("code");
        // 실제 서비스에서는 DB나 캐시에 저장된 인증 코드를 확인해야 합니다.
        // 여기서는 예시로 "1234"이면 인증 성공으로 가정합니다.
        if ("1234".equals(code)) {
            return ResponseEntity.ok("인증 코드 확인 성공 (예시)");
        } else {
            return ResponseEntity.badRequest().body("인증 코드가 올바르지 않습니다.");
        }
    }

    /**
     * JWT 토큰 추출 유틸 메서드
     * - Authorization 헤더에서 "Bearer " 접두어 제거 후 토큰 추출
     * - 헤더에 없으면 쿠키에서 "accessToken" 추출
     */
    private String extractToken(HttpServletRequest request) {
        String token = null;
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
        } else if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("accessToken".equals(cookie.getName())) {
                    token = cookie.getValue();
                    break;
                }
            }
        }
        return token;
    }
}
