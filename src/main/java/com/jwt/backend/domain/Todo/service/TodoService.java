package com.jwt.backend.domain.Todo.service;

import com.jwt.backend.domain.Todo.dto.request.TodoCreateRequestDto;
import com.jwt.backend.domain.Todo.dto.request.TodoListRequestDto;
import com.jwt.backend.domain.Todo.dto.response.TodoCreateResponseDto;
import com.jwt.backend.domain.Todo.dto.response.TodoListResponseDto;
import com.jwt.backend.domain.Todo.entity.Todo;
import com.jwt.backend.domain.member.entity.Member;
import com.jwt.backend.global.dto.request.PageRequestDto;
import com.jwt.backend.global.dto.response.PageResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface TodoService {
    ResponseEntity<TodoCreateResponseDto> create(TodoCreateRequestDto todoCreateRequestDto, Member principal);

    ResponseEntity<TodoListResponseDto> findTodoList(Pageable pageable, Member principal);
}
