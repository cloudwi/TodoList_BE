package com.jwt.backend.domain.member.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberLoginResponseDto {
    private String email;
    private String password;
}
