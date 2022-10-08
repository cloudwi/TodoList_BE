package com.jwt.backend.domain.member.controller;

import com.jwt.backend.domain.member.dto.request.MemberLoginRequestDto;
import com.jwt.backend.domain.member.dto.request.MemberSignUpRequestDto;
import com.jwt.backend.domain.member.dto.response.MemberLoginResponseDto;
import com.jwt.backend.domain.member.dto.response.MemberSignUpResponseDto;
import com.jwt.backend.domain.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "Member 컨트롤러")
@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @Operation(summary = "회원가입")
    @PostMapping()
    public ResponseEntity<MemberSignUpResponseDto> singUp(@RequestBody @Valid MemberSignUpRequestDto memberSignUpRequestDto) {
        return memberService.singUp(memberSignUpRequestDto);
    }

    @Operation(summary = "로그인")
    @PostMapping("/login")
    public ResponseEntity<MemberLoginResponseDto> login(@RequestBody @Valid MemberLoginRequestDto memberLoginRequestDto) {
        return memberService.login(memberLoginRequestDto);
    }

}
