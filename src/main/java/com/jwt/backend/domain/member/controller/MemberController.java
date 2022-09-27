package com.jwt.backend.domain.member.controller;

import com.jwt.backend.domain.member.dto.request.MemberSignUpRequestDto;
import com.jwt.backend.domain.member.dto.response.MemberLoginResponseDto;
import com.jwt.backend.domain.member.dto.response.MemberSignUpResponseDto;
import com.jwt.backend.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @PostMapping()
    public ResponseEntity<MemberSignUpResponseDto> singUp(@RequestBody @Valid MemberSignUpRequestDto memberSignUpRequestDto) {
        return memberService.singUp(memberSignUpRequestDto);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid MemberLoginResponseDto memberLoginResponseDto) {
        return memberService.login(memberLoginResponseDto);
    }
}
