package com.shop.subshop.service;

import com.shop.subshop.model.User;
import com.shop.subshop.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // SecurityConfig에서 Bean으로 등록한 BCryptPasswordEncoder 사용

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 회원가입 처리 - 비밀번호 암호화 후 사용자 저장
     */
    public User registerUser(User user) {
        // 비밀번호 암호화
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // 필요한 경우 상세 주소(detailAddress) 등 추가 정보 검증/처리 가능
        return userRepository.save(user);
    }

    /**
     * 로그인 처리 - 이메일과 비밀번호 검증
     */
    public Optional<User> authenticate(String email, String rawPassword) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // 비밀번호 일치 여부 확인 (암호화된 비밀번호와 비교)
            if (passwordEncoder.matches(rawPassword, user.getPassword())) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    /**
     * 모든 사용자 조회
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * 새로운 사용자 추가 (회원가입)
     */
    public User addUser(User user) {
        return registerUser(user);
    }

    /**
     * 회원정보 수정 (회원수정)
     */
    public Optional<User> updateUser(Long id, User updatedUser) {
        Optional<User> existingUserOpt = userRepository.findById(id);
        if (existingUserOpt.isPresent()) {
            User user = existingUserOpt.get();
            // 각 필드를 업데이트 (필요시 null 체크 추가)
            user.setUsername(updatedUser.getUsername());
            user.setEmail(updatedUser.getEmail());
            if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
                user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
            }
            user.setName(updatedUser.getName());
            user.setGender(updatedUser.getGender());
            user.setPhone(updatedUser.getPhone());
            user.setAddress(updatedUser.getAddress());
            user.setDetailAddress(updatedUser.getDetailAddress());
            user.setZipcode(updatedUser.getZipcode());
            // 추가 필드 (예: mileage, couponBox, role) 업데이트 필요시 처리
            return Optional.of(userRepository.save(user));
        }
        return Optional.empty();
    }

    /**
     * 회원 삭제 (회원삭제)
     */
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
