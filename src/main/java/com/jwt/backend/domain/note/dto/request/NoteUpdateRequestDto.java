package com.jwt.backend.domain.note.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoteUpdateRequestDto {
    @NotBlank(message = "아이디는 필수 입력입니다.")
    private String id;
    @NotBlank(message = "제목은 필수 입력입니다.")
    private String title;
    @NotBlank(message = "내용은 필수 입력입니다.")
    private String content;
    @NotBlank(message = "색상코드는 필수 입력입니다.")
    private String importance;
}
