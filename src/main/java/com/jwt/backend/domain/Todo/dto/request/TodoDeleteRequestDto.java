package com.jwt.backend.domain.Todo.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoDeleteRequestDto {
    private Long id;
}