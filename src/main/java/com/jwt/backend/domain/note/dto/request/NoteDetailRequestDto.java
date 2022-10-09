package com.jwt.backend.domain.note.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoteDetailRequestDto {
    @Schema(description = "Note 내용" , example = "1")
    @NotBlank(message = "아이디는 필수 입력입니다.")
    private Long id;
}
