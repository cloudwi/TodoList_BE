package com.jwt.backend.domain.todo.service.Impl;

import com.jwt.backend.domain.todo.dto.request.TodoCompletionRequestDto;
import com.jwt.backend.domain.todo.dto.request.TodoCreateRequestDto;
import com.jwt.backend.domain.todo.dto.request.TodoDeleteRequestDto;
import com.jwt.backend.domain.todo.dto.response.TodoCreateResponseDto;
import com.jwt.backend.domain.todo.dto.response.TodoListResponseDto;
import com.jwt.backend.domain.todo.entity.Todo;
import com.jwt.backend.domain.todo.repository.TodoRepository;
import com.jwt.backend.domain.todo.service.TodoService;
import com.jwt.backend.domain.member.entity.Member;
import com.jwt.backend.domain.member.repository.MemberRepository;
import com.jwt.backend.global.exception.CustomException;
import com.jwt.backend.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
*
*   TodoServiceImpl의 설명을 여기에 작성한다.
*
*   @author jangjuyeong
*   @version 1.0.0
*   작성일 2022/10/04
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

        Todo todo = new Todo(todoCreateRequestDto);

        Member findMember = findMember(principal.getId());

        todo.setMember(findMember);

        todoRepository.save(todo);

        TodoCreateResponseDto todoCreateResponseDto = new TodoCreateResponseDto(todo);

        log.info("save todo ID : {} ",todo.getId());

        return ResponseEntity
                .created(null)
                .body(todoCreateResponseDto);
    }

    @Override
    public ResponseEntity<List<TodoListResponseDto>> findList(Pageable pageable, Member principal) {

        Member findMember = findMember(principal.getId());

        Page<Todo> todoPage = todoRepository.findByMemberId(pageable, findMember.getId());

        List<TodoListResponseDto> todoList = new ArrayList<>();

        todoPage.forEach(todo -> todoList.add(new TodoListResponseDto(todo)));

        log.info("find todoList");

        return ResponseEntity.ok(todoList);
    }

    @Override
    @Transactional
    public ResponseEntity<Long> delete(TodoDeleteRequestDto todoDeleteRequestDto, Member principal) {

        Todo findTodo = findTodo(todoDeleteRequestDto.getId());

        Member findMember = findMember(principal.getId());

        todoMatchingMember(findMember, findTodo);

        todoRepository.delete(findTodo);

        log.info("delete todo ID : {}", findTodo.getId());

        return ResponseEntity.ok(findTodo.getId());
    }

    @Override
    @Transactional
    public ResponseEntity<Long> check(TodoCompletionRequestDto todoCompletionRequestDto, Member principal) {

        Todo findTodo = findTodo(todoCompletionRequestDto.getId());

        Member findMember = findMember(principal.getId());

        todoMatchingMember(findMember, findTodo);

        findTodo.Check();

        log.info("check todo ID : {}", findTodo.getId());

        return ResponseEntity.ok(findTodo.getId());
    }

    private Todo findTodo(Long id) {
        return todoRepository.findById(id)
                .orElseThrow(()-> {
                    throw new CustomException(ErrorCode.TODO_NOT_FOUND);
                });
    }

    private Member findMember(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(()->{
                    throw new CustomException(ErrorCode.MEMBER_NOT_FOUND);
                });
    }

    private void todoMatchingMember(Member member, Todo todo) {
        if (!member.getId().equals(todo.getMember().getId()))
            throw new CustomException(ErrorCode.TODO_NOT_FOUND);
    }
}

