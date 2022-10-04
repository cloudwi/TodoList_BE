package com.jwt.backend.domain.note.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoteDeleteRequestDto {
    private Long id;
}
