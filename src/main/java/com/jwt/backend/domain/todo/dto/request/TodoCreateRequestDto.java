package com.jwt.backend.domain.todo.dto.request;

import com.jwt.backend.domain.todo.entity.Todo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class TodoCreateRequestDto {
    @Schema(description = "Todo 내용" , example = "Todo Hello")
    @NotBlank(message = "내용은 필수 입력입니다.")
    private String content;
}
