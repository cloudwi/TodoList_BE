package com.jwt.backend.domain.Todo.service;

import com.jwt.backend.domain.Todo.dto.request.TodoCreateRequestDto;
import com.jwt.backend.domain.Todo.dto.response.TodoCreateResponseDto;
import com.jwt.backend.domain.Todo.dto.response.TodoListResponseDto;
import com.jwt.backend.domain.Todo.entity.Todo;
import com.jwt.backend.domain.member.entity.Member;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TodoService {
    ResponseEntity<TodoCreateResponseDto> create(TodoCreateRequestDto todoCreateRequestDto, Member principal);

    ResponseEntity<List<TodoListResponseDto>> findTodoList(Pageable pageable, Member principal);

    default TodoListResponseDto entityToDto(Todo todo) {
        TodoListResponseDto dto = TodoListResponseDto.builder()
                .id(todo.getId())
                .content(todo.getContent())
                .build();

        return dto;
    }
}
