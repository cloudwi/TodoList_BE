package com.jwt.backend.domain.todo.dto.request;

import com.jwt.backend.domain.todo.entity.Todo;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class TodoCreateRequestDto {
    @NotBlank(message = "내용은 필수 입력입니다.")
    private String content;

    public Todo toEntity() {
        return Todo.builder()
                .content(content)
                .build();
    }
}
