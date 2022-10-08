package com.jwt.backend.domain.member.dto.request;

import com.jwt.backend.domain.member.entity.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberSignUpRequestDto {

    @Schema(description = "회원 이메일" , example = "cloudwi@naver.com")
    @Email(message = "올바른 이메일의 형식이 아닙니다.")
    @NotBlank(message = "이메일은 필수 입력입니다.")
    private String email;

    @Schema(description = "회원 비밀번호" , example = "Qw123!")
    @NotBlank(message = "비밀번호는 필수 입력입니다.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{6,20}",
            message = "영문 대,소문자, 숫자, 특수기호가 적어도 1개 이상씩 포함된 6자 ~ 20자여야 합니다.")
    private String password;

    @Schema(description = "회원 닉네임" , example = "구름")
    @NotBlank(message = "닉네임은 필수 입력입니다.")
    private String nickname;
}
