package com.jwt.backend.domain.note.repository;

import com.jwt.backend.domain.note.entity.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
    Page<Note> findByMemberId(Pageable pageable, Long id);
}
