package com.jwt.backend.domain.todo.entity;

import com.jwt.backend.domain.todo.dto.request.TodoCreateRequestDto;
import com.jwt.backend.domain.todo.dto.response.TodoListResponseDto;
import com.jwt.backend.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "todo")
@Slf4j
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private boolean checkTodo = false;

    public Todo(TodoCreateRequestDto todoCreateRequestDto) {
        this.content = todoCreateRequestDto.getContent();
    }

    public void Check() {
        this.checkTodo = !this.checkTodo;
    }

    public void setMember(Member member) {
        if (this.member != null) {
            this.member.getTodoList().remove(this);
        }
        this.member = member;
        member.getTodoList().add(this);
    }

    public boolean getCheckTodo() {
        return this.checkTodo;
    }


}
