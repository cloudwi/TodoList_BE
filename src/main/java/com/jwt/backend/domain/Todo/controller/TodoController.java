package com.jwt.backend.domain.Todo.controller;

import com.jwt.backend.domain.Todo.dto.request.TodoCreateRequestDto;
import com.jwt.backend.domain.Todo.dto.request.TodoDeleteRequestDto;
import com.jwt.backend.domain.Todo.dto.request.TodoCompletionRequestDto;
import com.jwt.backend.domain.Todo.dto.response.TodoCreateResponseDto;
import com.jwt.backend.domain.Todo.dto.response.TodoListResponseDto;
import com.jwt.backend.domain.Todo.service.TodoService;
import com.jwt.backend.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    public ResponseEntity<List<TodoListResponseDto>> findList(@PageableDefault(sort = "id",
            direction = Sort.Direction.DESC)
                                                       Pageable pageable, Authentication authentication) {

        return todoService.findList(pageable, (Member) authentication.getPrincipal());
    }

    @DeleteMapping()
    public ResponseEntity<Long> delete(@RequestBody TodoDeleteRequestDto todoDeleteRequestDto,
                                       Authentication authentication) {
        return todoService.delete(todoDeleteRequestDto, (Member) authentication.getPrincipal());
    }

    @PutMapping()
    public ResponseEntity<Long> completion(@RequestBody TodoCompletionRequestDto todoCompletionRequestDto,
                                           Authentication authentication) {
        return todoService.completion(todoCompletionRequestDto, (Member) authentication.getPrincipal());
    }
}
