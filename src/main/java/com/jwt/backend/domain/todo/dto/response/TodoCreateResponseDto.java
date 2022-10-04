package com.jwt.backend.domain.todo.dto.response;


import com.jwt.backend.domain.todo.entity.Todo;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodoCreateResponseDto {
    private Long id;

    public TodoCreateResponseDto(Todo todo) {
        this.id = todo.getId();
    }
}
