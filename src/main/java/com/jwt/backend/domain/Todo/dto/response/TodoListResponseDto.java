package com.jwt.backend.domain.Todo.dto.response;

import com.jwt.backend.domain.Todo.entity.Todo;
import lombok.*;
import org.springframework.data.domain.Page;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodoListResponseDto {
    private Page<Todo> todoPage;
}
