package com.jwt.backend.domain.note.dto.response;

import com.jwt.backend.domain.note.entity.Note;

import java.time.LocalDateTime;

public class NoteDetailResponseDto {
    private String title;
    private String content;
    private LocalDateTime modifiedDate;

    public NoteDetailResponseDto(Note note) {
        this.title = note.getTitle();
        this.content = note.getContent();
        this.modifiedDate = note.getModifiedDate();
    }
}
