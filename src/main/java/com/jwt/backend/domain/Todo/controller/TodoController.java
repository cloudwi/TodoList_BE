package com.jwt.backend.domain.Todo.controller;

import com.jwt.backend.domain.Todo.dto.request.TodoCreateRequestDto;
import com.jwt.backend.domain.Todo.dto.response.TodoCreateResponseDto;
import com.jwt.backend.domain.Todo.dto.response.TodoListResponseDto;
import com.jwt.backend.domain.Todo.entity.Todo;
import com.jwt.backend.domain.Todo.service.TodoService;
import com.jwt.backend.domain.member.entity.Member;
import com.jwt.backend.global.dto.request.PageRequestDto;
import com.jwt.backend.global.dto.response.PageResponseDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoController {
    private final TodoService todoService;

    @PostMapping()
    public ResponseEntity<TodoCreateResponseDto> create(@RequestBody @Valid TodoCreateRequestDto todoCreateRequestDto,
                                                        Authentication authentication) {

        return todoService.create(todoCreateRequestDto, (Member) authentication.getPrincipal());
    }

    @GetMapping()
    public ResponseEntity<List<TodoListResponseDto>> fondTodoList(@PageableDefault(sort = "id", direction = Sort.Direction.DESC)
                                                       Pageable pageable, Authentication authentication) {

        return todoService.findTodoList(pageable, (Member) authentication.getPrincipal());
    }
}
