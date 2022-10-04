package com.jwt.backend.domain.member.service.Impl;

import com.jwt.backend.domain.member.dto.request.MemberLoginRequestDto;
import com.jwt.backend.domain.member.entity.Member;
import com.jwt.backend.domain.member.dto.request.MemberSignUpRequestDto;
import com.jwt.backend.domain.member.dto.response.MemberLoginResponseDto;
import com.jwt.backend.domain.member.dto.response.MemberSignUpResponseDto;
import com.jwt.backend.domain.member.repository.MemberRepository;
import com.jwt.backend.domain.member.service.MemberService;
import com.jwt.backend.global.exception.CustomException;
import com.jwt.backend.global.exception.ErrorCode;
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

        validateMatchedEmail(memberSignUpRequestDto.getEmail());

        Member member = new Member(memberSignUpRequestDto, passwordEncoder);

        memberRepository.save(member);

        MemberSignUpResponseDto memberSignUpResponseDto = new MemberSignUpResponseDto(member.getId());

        return ResponseEntity.created(null).body(memberSignUpResponseDto);
    }

    @Override
    public ResponseEntity<MemberLoginResponseDto> login(MemberLoginRequestDto memberLoginRequestDto) {

        Member findMember = findMember(memberLoginRequestDto.getEmail());

        validateMatchedPassword(memberLoginRequestDto.getPassword(), findMember.getPassword());

        String accessToken = jwtTokenProvider.createAccessToken(findMember.getUsername(), findMember.getRole().name());

        MemberLoginResponseDto memberLoginResponseDto = new MemberLoginResponseDto(findMember, accessToken);

        return ResponseEntity.ok(memberLoginResponseDto);
    }

    private void validateMatchedEmail(String email) {
        if (memberRepository.findByEmail(email).isPresent()) {
            throw new CustomException(ErrorCode.EXIST_MEMBER);
        }
    }

    private void validateMatchedPassword(String validPassword, String memberPassword) {
        if (!passwordEncoder.matches(validPassword, memberPassword)) {
            throw new CustomException(ErrorCode.NOT_FOUND_PASSWORD);
        }
    }

    private Member findMember(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(()->{
                    throw new CustomException(ErrorCode.NOT_FOUND_MEMBER);
                });
    }
}
