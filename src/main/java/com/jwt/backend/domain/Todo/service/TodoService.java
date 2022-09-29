package com.jwt.backend.domain.Todo.service;

import com.jwt.backend.domain.Todo.dto.request.TodoCreateRequestDto;
import com.jwt.backend.domain.Todo.dto.request.TodoDeleteRequestDto;
import com.jwt.backend.domain.Todo.dto.request.TodoCompletionRequestDto;
import com.jwt.backend.domain.Todo.dto.response.TodoCreateResponseDto;
import com.jwt.backend.domain.Todo.dto.response.TodoListResponseDto;
import com.jwt.backend.domain.member.entity.Member;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TodoService {
    ResponseEntity<TodoCreateResponseDto> create(TodoCreateRequestDto todoCreateRequestDto, Member principal);

    ResponseEntity<List<TodoListResponseDto>> findList(Pageable pageable, Member principal);

    ResponseEntity<Long> delete(TodoDeleteRequestDto todoDeleteRequestDto, Member principal);

    ResponseEntity<Long> completion(TodoCompletionRequestDto todoCompletionRequestDto, Member principal);
}
