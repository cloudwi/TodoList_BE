package com.jwt.backend.domain.note.controller;

import com.jwt.backend.domain.member.entity.Member;
import com.jwt.backend.domain.note.dto.request.NoteCreateReqestDto;
import com.jwt.backend.domain.note.dto.response.NoteCreateResponseDto;
import com.jwt.backend.domain.note.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("note")
public class NoteController {

    private final NoteService noteService;

    @PostMapping()
    public ResponseEntity<NoteCreateResponseDto> create(@RequestBody @Valid NoteCreateReqestDto noteCreateReqestDto, Authentication authentication) {
        return noteService.create(noteCreateReqestDto, (Member) authentication.getPrincipal());
    }
}
