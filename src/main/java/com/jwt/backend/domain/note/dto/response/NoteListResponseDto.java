package com.jwt.backend.domain.note.dto.response;

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
}
