package com.jwt.backend.domain.note.dto.response;

import com.jwt.backend.domain.note.entity.Note;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoteCreateResponseDto {
    private Long id;

    public NoteCreateResponseDto(Note note) {
        this.id = note.getId();
    }
}
