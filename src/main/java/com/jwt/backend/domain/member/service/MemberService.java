package com.jwt.backend.domain.member.service;

import com.jwt.backend.domain.member.dto.request.MemberSignUpRequestDto;
import com.jwt.backend.domain.member.dto.response.MemberLoginResponseDto;
import com.jwt.backend.domain.member.dto.response.MemberSignUpResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {
    ResponseEntity<MemberSignUpResponseDto> singUp(MemberSignUpRequestDto memberSignUpRequestDto);

    ResponseEntity<String> login(MemberLoginResponseDto memberLoginResponseDto);
}
