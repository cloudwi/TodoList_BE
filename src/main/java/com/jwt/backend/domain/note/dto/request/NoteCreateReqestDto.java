package com.jwt.backend.domain.note.dto.request;

import com.jwt.backend.domain.note.entity.Note;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoteCreateReqestDto {
    @Schema(description = "Note 제목" , example = "Note Hello")
    @NotBlank(message = "제목은 필수 입력입니다.")
    private String title;
    @Schema(description = "Note 내용" , example = "Hello World!")
    @NotBlank(message = "내용은 필수 입력입니다.")
    private String content;
    @Schema(description = "Note 색상코드" , example = "#111111")
    @NotBlank(message = "색상코드는 필수 입력입니다.")
    private String importance;

    public Note toEntity() {
        return Note.builder()
                .title(this.title)
                .content(this.content)
                .importance(this.importance)
                .build();
    }
}
