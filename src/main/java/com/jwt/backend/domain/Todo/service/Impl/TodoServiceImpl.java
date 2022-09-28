package com.jwt.backend.domain.Todo.service.Impl;

import com.jwt.backend.domain.Todo.dto.request.TodoCreateRequestDto;
import com.jwt.backend.domain.Todo.dto.request.TodoListRequestDto;
import com.jwt.backend.domain.Todo.dto.response.TodoCreateResponseDto;
import com.jwt.backend.domain.Todo.dto.response.TodoListResponseDto;
import com.jwt.backend.domain.Todo.entity.Todo;
import com.jwt.backend.domain.Todo.repository.TodoRepository;
import com.jwt.backend.domain.Todo.service.TodoService;
import com.jwt.backend.domain.member.entity.Member;
import com.jwt.backend.domain.member.exception.MemberException;
import com.jwt.backend.domain.member.exception.MemberExceptionType;
import com.jwt.backend.domain.member.repository.MemberRepository;
import com.jwt.backend.global.dto.request.PageRequestDto;
import com.jwt.backend.global.dto.response.PageResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
*
*   TodoServiceImpl의 설명을 여기에 작성한다.
*
*   @author jangjuyeong
*   @version 1.0.0
*   작성일 2022/09/27
**/

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public ResponseEntity<TodoCreateResponseDto> create(TodoCreateRequestDto todoCreateRequestDto, Member principal) {
        Todo todo = todoCreateRequestDto.toEntity();
        Member member = memberRepository.findById(principal.getId())
                .orElseThrow(()->{
                    throw new MemberException(MemberExceptionType.NOT_SIGNUP_EMAIL);
                });

        todo.setMember(member);

        todoRepository.save(todo);

        TodoCreateResponseDto todoCreateResponseDto = new TodoCreateResponseDto().builder()
                .id(todo.getId())
                .build();

        return ResponseEntity
                .created(null)
                .body(todoCreateResponseDto);
    }

    @Override
    public ResponseEntity<TodoListResponseDto> findTodoList(Pageable pageable, Member principal) {

        return null;
    }

}
