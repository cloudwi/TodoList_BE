package com.jwt.backend.domain.todo.repository;

import com.jwt.backend.domain.todo.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    Page<Todo> findByMemberId(Pageable pageable, Long id);

    Long countByMemberId(Long id);
}
