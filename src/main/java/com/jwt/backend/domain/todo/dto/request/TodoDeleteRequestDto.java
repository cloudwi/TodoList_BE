package com.jwt.backend.domain.todo.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoDeleteRequestDto {
    private Long id;
}
