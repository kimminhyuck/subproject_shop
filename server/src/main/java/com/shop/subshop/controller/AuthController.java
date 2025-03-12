package com.shop.subshop.controller;

import com.shop.subshop.model.User;
import com.shop.subshop.repository.UserRepository;
import com.shop.subshop.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    /**
     * ✅ 회원가입 API
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String email = request.get("email");
        String password = request.get("password");
        String name = request.get("name");
        String gender = request.get("gender");
        String phone = request.get("phone");
        String address = request.get("address");
        String detailAddress = request.get("detailAddress"); // 상세 주소 필드 포함
        String zipcode = request.get("zipcode");

        // 이미 존재하는 이메일 확인
        if (userRepository.findByEmail(email).isPresent()) {
            return ResponseEntity.badRequest().body("이미 가입된 이메일입니다.");
        }

        // 비밀번호 암호화 후 저장
        User newUser = User.builder()
                .username(username)
                .email(email)
                .password(passwordEncoder.encode(password)) // 암호화 저장
                .name(name)
                .gender(gender)
                .phone(phone)
                .address(address)
                .detailAddress(detailAddress) // 상세 주소 저장
                .zipcode(zipcode)
                .role("ROLE_USER") // 기본 ROLE_USER
                .build();

        userRepository.save(newUser);
        return ResponseEntity.ok("회원가입 성공");
    }

    /**
     * ✅ 로그인 API (JWT 발급)
     */
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String password = request.get("password");

        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // 비밀번호 검증
            if (passwordEncoder.matches(password, user.getPassword())) {
                String token = jwtUtil.generateToken(user.getEmail());
                return ResponseEntity.ok(Map.of("token", token));
            }
        }

        return ResponseEntity.status(401).body("이메일 또는 비밀번호가 올바르지 않습니다.");
    }

    /**
     * ✅ 사용자 정보 조회 API (JWT 필요)
     */
    @GetMapping("/me")
    public ResponseEntity<?> getUserInfo(@RequestHeader("Authorization") String token) {
        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body("인증 토큰이 필요합니다.");
        }

        String jwt = token.substring(7);
        if (!jwtUtil.validateToken(jwt)) {
            return ResponseEntity.status(401).body("유효하지 않은 토큰입니다.");
        }

        String email = jwtUtil.extractUsername(jwt);
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return ResponseEntity.ok(Map.ofEntries(
                Map.entry("id", user.getId()),
                Map.entry("username", user.getUsername()),
                Map.entry("email", user.getEmail()),
                Map.entry("name", user.getName()),
                Map.entry("gender", user.getGender()),
                Map.entry("phone", user.getPhone()),
                Map.entry("address", user.getAddress()),
                Map.entry("detailAddress", user.getDetailAddress()), // 상세 주소 포함
                Map.entry("zipcode", user.getZipcode()),
                Map.entry("mileage", user.getMileage()),
                Map.entry("role", user.getRole())
            ));
        }

        return ResponseEntity.status(404).body("사용자를 찾을 수 없습니다.");
    }
}
