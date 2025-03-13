package com.shop.subshop.service;

import com.shop.subshop.model.User;
import com.shop.subshop.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    // UserRepository: DB와의 CRUD 작업을 담당
    private final UserRepository userRepository;
    // PasswordEncoder: 비밀번호 암호화를 위해 BCryptPasswordEncoder 사용
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 회원가입 처리 메서드
     * - 전달받은 User 객체의 비밀번호를 암호화한 후 저장합니다.
     * - 추가 검증 로직(예: 상세 주소 검증 등)이 필요하면 이곳에 추가할 수 있습니다.
     *
     * @param user 회원가입 요청으로 받은 사용자 정보
     * @return 저장된 User 객체
     */
    public User registerUser(User user) {
        // 비밀번호 암호화
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // 추가 검증/처리가 필요한 경우 여기서 구현 (예: 상세 주소, 전화번호 형식 등)
        return userRepository.save(user);
    }

    /**
     * 로그인 처리 메서드
     * - 이메일로 사용자 정보를 조회한 후, 입력된 비밀번호가 암호화된 비밀번호와 일치하는지 확인합니다.
     *
     * @param email      사용자가 입력한 이메일
     * @param rawPassword 사용자가 입력한 비밀번호 (평문)
     * @return 인증된 사용자가 존재하면 Optional<User> 반환, 아니면 Optional.empty()
     */
    public Optional<User> authenticate(String email, String rawPassword) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // BCrypt 암호화된 비밀번호와 비교
            if (passwordEncoder.matches(rawPassword, user.getPassword())) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    /**
     * 모든 사용자 조회 메서드
     *
     * @return 전체 사용자 목록
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * 새로운 사용자 추가 메서드 (회원가입과 동일)
     *
     * @param user 추가할 사용자 정보
     * @return 저장된 User 객체
     */
    public User addUser(User user) {
        return registerUser(user);
    }

    /**
     * 회원 정보 수정 메서드
     * - 사용자 ID로 기존 사용자를 조회한 후, 필요한 필드를 업데이트합니다.
     * - 비밀번호 변경 요청 시에는 새 비밀번호가 제공되면 암호화하여 저장합니다.
     *
     * @param id          수정할 사용자의 ID
     * @param updatedUser 수정할 데이터가 포함된 User 객체
     * @return 수정된 User 객체가 존재하면 Optional로 반환, 없으면 Optional.empty()
     */
    public Optional<User> updateUser(Long id, User updatedUser) {
        Optional<User> existingUserOpt = userRepository.findById(id);
        if (existingUserOpt.isPresent()) {
            User user = existingUserOpt.get();
            // 필드 업데이트: 필요에 따라 null 체크 등을 수행할 수 있습니다.
            user.setUsername(updatedUser.getUsername());
            user.setEmail(updatedUser.getEmail());
            if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
                // 새 비밀번호가 제공되면 암호화 후 저장
                user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
            }
            user.setName(updatedUser.getName());
            user.setGender(updatedUser.getGender());
            user.setPhone(updatedUser.getPhone());
            user.setAddress(updatedUser.getAddress());
            user.setDetailAddress(updatedUser.getDetailAddress());
            user.setZipcode(updatedUser.getZipcode());
            // 추가 필드(예: mileage, couponBox, role) 수정 로직이 필요하면 추가
            return Optional.of(userRepository.save(user));
        }
        return Optional.empty();
    }

    /**
     * 회원 삭제 메서드
     * - 주어진 사용자 ID로 사용자를 삭제합니다.
     *
     * @param id 삭제할 사용자 ID
     */
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
