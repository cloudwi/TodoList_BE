package com.jwt.backend.domain.todo.dto.request;

import com.jwt.backend.domain.todo.entity.Todo;
import lombok.Data;

@Data
public class TodoCreateRequestDto {
    private String content;

    public Todo toEntity() {
        return Todo.builder()
                .content(content)
                .build();
    }
}
