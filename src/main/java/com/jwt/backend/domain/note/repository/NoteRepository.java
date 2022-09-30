package com.jwt.backend.domain.note.repository;

import com.jwt.backend.domain.note.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
}
