package com.jwt.backend.domain.todo.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoCompletionRequestDto {
    @NotBlank(message = "아이디는 필수 입력입니다.")
    private Long id;
}
