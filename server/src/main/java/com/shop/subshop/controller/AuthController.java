package com.shop.subshop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class AuthController {

    @GetMapping("/test")
    public ResponseEntity<String> publicEndpoint() {
        return ResponseEntity.ok(" 이 페이지는 인증 없이 접근할 수 있습니다. 디비연결성공 ");
    }
}
