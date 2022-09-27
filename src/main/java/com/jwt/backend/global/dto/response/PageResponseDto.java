package com.jwt.backend.global.dto.response;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PageResponseDto<dto, en> {
    private List<dto> dtoList;

    public PageResponseDto(Page<en> result, Function<en, dto> fn) {
        dtoList = result.stream().map(fn).collect(Collectors.toList());
    }
}