package com.shop.subshop.controller;


import com.shop.subshop.model.User;
import com.shop.subshop.service.UserService;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 모든 사용자 조회
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // 사용자 추가
    @PostMapping
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }
}
