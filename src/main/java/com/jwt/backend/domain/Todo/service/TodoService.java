package com.jwt.backend.domain.Todo.service;

import com.jwt.backend.domain.Todo.dto.request.TodoCreateRequestDto;
import com.jwt.backend.domain.Todo.dto.response.TodoCreateResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface TodoService {
    ResponseEntity<TodoCreateResponseDto> create(TodoCreateRequestDto todoCreateRequestDto);
//    ResponseEntity<Page<TodoFind>>
}
