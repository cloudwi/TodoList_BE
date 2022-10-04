package com.jwt.backend.domain.note.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jwt.backend.domain.note.entity.Note;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoteListResponseDto {
    private Long id;
    private String title;
    private String importance;
    private LocalDateTime modifiedDate;

    public NoteListResponseDto(Note note) {
        this.id = note.getId();
        this.title = note.getTitle();
        this.importance = note.getImportance();
        this.modifiedDate = note.getModifiedDate();
    }
}
