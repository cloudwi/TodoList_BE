package com.jwt.backend.domain.Todo.service.Impl;

import com.jwt.backend.domain.Todo.dto.request.TodoCreateRequestDto;
import com.jwt.backend.domain.Todo.dto.request.TodoDeleteRequestDto;
import com.jwt.backend.domain.Todo.dto.response.TodoCreateResponseDto;
import com.jwt.backend.domain.Todo.dto.response.TodoListResponseDto;
import com.jwt.backend.domain.Todo.entity.Todo;
import com.jwt.backend.domain.Todo.exception.TodoException;
import com.jwt.backend.domain.Todo.exception.TodoExceptionType;
import com.jwt.backend.domain.Todo.repository.TodoRepository;
import com.jwt.backend.domain.Todo.service.TodoService;
import com.jwt.backend.domain.member.entity.Member;
import com.jwt.backend.domain.member.exception.MemberException;
import com.jwt.backend.domain.member.exception.MemberExceptionType;
import com.jwt.backend.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
    public ResponseEntity<List<TodoListResponseDto>> findList(Pageable pageable, Member principal) {
        Member member = memberRepository.findById(principal.getId())
                .orElseThrow(()->{
                    throw new MemberException(MemberExceptionType.NOT_SIGNUP_EMAIL);
                });

        List<Todo> findTodoList = member.getTodoList();

        List<TodoListResponseDto> TodoList = new ArrayList<>();

        findTodoList.forEach(todo -> {
            TodoList.add(todo.EntityToDto());
        });

        return ResponseEntity.ok(TodoList);
    }

    @Override
    @Transactional
    public ResponseEntity<Long> delete(TodoDeleteRequestDto todoDeleteRequestDto, Member principal) {

        Todo todo = todoRepository.findById(todoDeleteRequestDto.getId())
                .orElseThrow(()-> {
                    throw new TodoException(TodoExceptionType.NOT_FOUND_TODO);
                });

        Member member = memberRepository.findById(principal.getId())
                .orElseThrow(()->{
                    throw new MemberException(MemberExceptionType.NOT_SIGNUP_EMAIL);
                });;

        if (todo.getMember().getId() != member.getId())
            throw new TodoException(TodoExceptionType.NOT_MATCHING_TODO);

        todoRepository.delete(todo);
        log.info("delete todo");

        return ResponseEntity.ok(todo.getId());
    }
}
