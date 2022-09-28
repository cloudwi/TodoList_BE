package com.jwt.backend.domain.Todo.dto.response;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodoListResponseDto {
    private Long id;
    private String content;
}
