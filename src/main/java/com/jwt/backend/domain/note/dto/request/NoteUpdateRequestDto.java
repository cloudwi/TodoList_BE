package com.jwt.backend.domain.note.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoteUpdateRequestDto {
    @Schema(description = "Note 아이디" , example = "1")
    @NotBlank(message = "아이디는 필수 입력입니다.")
    private String id;
    @Schema(description = "Note 제목" , example = "Hello 수정완료")
    @NotBlank(message = "제목은 필수 입력입니다.")
    private String title;
    @Schema(description = "Note 내용" , example = "Hello World! 수정완료")
    @NotBlank(message = "내용은 필수 입력입니다.")
    private String content;
    @Schema(description = "Note 색상코드" , example = "#111111 수정완료")
    @NotBlank(message = "색상코드는 필수 입력입니다.")
    private String importance;
}
