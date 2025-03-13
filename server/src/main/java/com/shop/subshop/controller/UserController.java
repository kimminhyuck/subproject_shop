package com.shop.subshop.controller;

import com.shop.subshop.model.User;
import com.shop.subshop.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 관리자 전용: 모든 사용자 조회
     */
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * 관리자 전용: 사용자 추가
     */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    /**
     * 관리자 전용: 사용자 수정
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User userData) {
        Optional<User> updatedUser = userService.updateUser(id, userData);
        if (updatedUser.isPresent()) {
            return ResponseEntity.ok(updatedUser.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("사용자를 찾을 수 없습니다.");
        }
    }

    /**
     * 관리자 전용: 사용자 삭제
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("사용자 삭제 성공");
    }
}
