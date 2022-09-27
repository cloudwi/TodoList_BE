package com.jwt.backend.domain.member.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberLoginResponseDto {
    private String nickname;
    private String token;
}
