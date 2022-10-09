package com.jwt.backend.domain.note.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoteDeleteRequestDto {

    @Schema(description = "Note id" , example = "1")
    private Long id;
}
