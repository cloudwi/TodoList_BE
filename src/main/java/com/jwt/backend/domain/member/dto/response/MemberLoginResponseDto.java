package com.jwt.backend.domain.member.dto.response;

import com.jwt.backend.domain.member.entity.Member;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberLoginResponseDto {
    private String nickname;
    private String token;

    public MemberLoginResponseDto(Member member, String accessToken) {
        this.nickname = member.getNickname();
        this.token = accessToken;
    }
}
