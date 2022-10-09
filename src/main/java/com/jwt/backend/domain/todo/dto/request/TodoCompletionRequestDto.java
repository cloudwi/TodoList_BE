package com.jwt.backend.domain.todo.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoCompletionRequestDto {
    @Schema(description = "Todo 아이디" , example = "1")
    @NotBlank(message = "아이디는 필수 입력입니다.")
    private Long id;
}
