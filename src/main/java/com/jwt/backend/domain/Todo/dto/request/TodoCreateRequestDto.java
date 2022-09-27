package com.jwt.backend.domain.Todo.dto.request;

import com.jwt.backend.domain.Todo.entity.Todo;
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
