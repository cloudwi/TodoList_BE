package com.jwt.backend.domain.member.service.Impl;

import com.jwt.backend.domain.member.Member;
import com.jwt.backend.domain.member.dto.request.MemberSignUpRequestDto;
import com.jwt.backend.domain.member.dto.response.MemberLoginResponseDto;
import com.jwt.backend.domain.member.dto.response.MemberSignUpResponseDto;
import com.jwt.backend.domain.member.exception.MemberException;
import com.jwt.backend.domain.member.exception.MemberExceptionType;
import com.jwt.backend.domain.member.repository.MemberRepository;
import com.jwt.backend.domain.member.service.MemberService;
import com.jwt.backend.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
*
*   MemberServiceImpl의 설명을 여기에 작성한다.
*
*   @author jangjuyeong
*   @version 1.0.0
*   작성일 2022/09/27
**/

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    @Override
    public ResponseEntity<MemberSignUpResponseDto> singUp(MemberSignUpRequestDto memberSignUpRequestDto) {
        if (memberRepository.findByEmail(memberSignUpRequestDto.getEmail()).isPresent()) {
            throw new MemberException(MemberExceptionType.ALREADY_EXIST_EMAIL);
        }

        Member member = memberRepository.save(memberSignUpRequestDto.toEntity());
        member.addUserAuthority();
        member.encodePassword(passwordEncoder);

        MemberSignUpResponseDto memberSignUpResponseDto = MemberSignUpResponseDto.builder()
                .id(member.getId())
                .build();
        return ResponseEntity.created(null).body(memberSignUpResponseDto);
    }

    @Override
    public ResponseEntity<String> login(MemberLoginResponseDto memberLoginResponseDto) {
        Member member = memberRepository.findByEmail(memberLoginResponseDto.getEmail())
                .orElseThrow(() -> new MemberException(MemberExceptionType.NOT_SIGNUP_EMAIL));
        validateMatchedPassword(memberLoginResponseDto.getPassword(), member.getPassword());

        String accessToken = jwtTokenProvider.createAccessToken(member.getUsername(), member.getRole().name());

        return ResponseEntity.ok(accessToken);
    }

    private void validateMatchedPassword(String validPassword, String memberPassword) {
        if (!passwordEncoder.matches(validPassword, memberPassword)) {
            throw new MemberException(MemberExceptionType.WRONG_PASSWORD);
        }
    }
}
