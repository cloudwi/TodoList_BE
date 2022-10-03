package com.jwt.backend.domain.todo.dto.response;

import com.jwt.backend.domain.todo.entity.Todo;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodoListResponseDto {
    private Long id;
    private String content;
    private boolean checkTodo;

    public TodoListResponseDto(Todo todo) {
        this.id = todo.getId();
        this.content = todo.getContent();
        this.checkTodo = todo.getCheckTodo();
    }
}
