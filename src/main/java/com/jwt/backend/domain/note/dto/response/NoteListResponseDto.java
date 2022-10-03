package com.jwt.backend.domain.note.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoteListResponseDto {
    private Long id;
    private String title;
    private String content;
    private String importance;
    private String modifiedDate;
}
