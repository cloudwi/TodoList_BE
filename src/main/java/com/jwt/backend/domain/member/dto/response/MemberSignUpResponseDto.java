package com.jwt.backend.domain.member.dto.response;

import com.jwt.backend.domain.member.dto.request.MemberSignUpRequestDto;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberSignUpResponseDto {
    private Long id;

    public MemberSignUpResponseDto(Long id) {
        this.id = id;
    }
}
