package com.jwt.backend.global.test;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Operation(summary = "토큰의 테스트를 위해서 사용한다.")
    @GetMapping()
    public ResponseEntity<String> test() {
        return ResponseEntity.ok().body("테스트 성공");
    }
}
