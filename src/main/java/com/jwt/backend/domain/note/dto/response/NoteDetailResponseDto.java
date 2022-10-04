package com.jwt.backend.domain.note.dto.response;

import com.jwt.backend.domain.note.entity.Note;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NotBlank
@Builder
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
