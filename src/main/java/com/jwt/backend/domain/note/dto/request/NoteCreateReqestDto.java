package com.jwt.backend.domain.note.dto.request;

import com.jwt.backend.domain.note.entity.Note;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoteCreateReqestDto {
    private String title;
    private String content;
    private String importance;

    public Note toEntity() {
        return Note.builder()
                .title(this.title)
                .content(this.content)
                .importance(this.importance)
                .build();
    }
}
