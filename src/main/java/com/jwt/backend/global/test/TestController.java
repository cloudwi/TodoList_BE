package com.jwt.backend.global.test;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping()
    public ResponseEntity<String> test() {
        return ResponseEntity.ok().body("테스트 성공");
    }
}
