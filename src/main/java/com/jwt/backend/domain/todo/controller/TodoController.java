package com.jwt.backend.domain.todo.controller;

import com.jwt.backend.domain.todo.dto.request.TodoCreateRequestDto;
import com.jwt.backend.domain.todo.dto.request.TodoDeleteRequestDto;
import com.jwt.backend.domain.todo.dto.request.TodoCompletionRequestDto;
import com.jwt.backend.domain.todo.dto.response.TodoCreateResponseDto;
import com.jwt.backend.domain.todo.dto.response.TodoListResponseDto;
import com.jwt.backend.domain.todo.service.TodoService;
import com.jwt.backend.domain.member.entity.Member;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Todo 컨트롤러")
@RestController
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoController {
    private final TodoService todoService;

    @Operation(summary = "Todo 생성")
    @PostMapping()
    public ResponseEntity<TodoCreateResponseDto> create(@RequestBody @Valid TodoCreateRequestDto todoCreateRequestDto,
                                                        Authentication authentication) {

        return todoService.create(todoCreateRequestDto, (Member) authentication.getPrincipal());
    }

    @Operation(summary = "해당 회원이 작성한 TodoList 조회")
    @GetMapping()
    public ResponseEntity<List<TodoListResponseDto>> findList(@PageableDefault(value = 9, sort = "id",
            direction = Sort.Direction.DESC) Pageable pageable, Authentication authentication) {
        return todoService.findList(pageable, (Member) authentication.getPrincipal());
    }

    @Operation(summary = "해당 회원이 작성한 Todo 삭제")
    @DeleteMapping()
    public ResponseEntity<Long> delete(@RequestBody TodoDeleteRequestDto todoDeleteRequestDto,
                                       Authentication authentication) {
        return todoService.delete(todoDeleteRequestDto, (Member) authentication.getPrincipal());
    }

    @Operation(summary = "해당 회원이 작성한 Todo를 수행하면 체크상태 변경")
    @PutMapping()
    public ResponseEntity<Long> completion(@RequestBody TodoCompletionRequestDto todoCompletionRequestDto,
                                           Authentication authentication) {
        return todoService.check(todoCompletionRequestDto, (Member) authentication.getPrincipal());
    }

    @Operation(summary = "해당 회원이 작성한 TodoList의 갯수")
    @GetMapping("/count")
    public ResponseEntity<Long> count(Authentication authentication) {
        return todoService.count((Member) authentication.getPrincipal());
    }
}
