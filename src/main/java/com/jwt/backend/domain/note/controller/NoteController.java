package com.jwt.backend.domain.note.controller;

import com.jwt.backend.domain.member.entity.Member;
import com.jwt.backend.domain.note.dto.request.NoteCreateReqestDto;
import com.jwt.backend.domain.note.dto.request.NoteDeleteRequestDto;
import com.jwt.backend.domain.note.dto.request.NoteUpdateRequestDto;
import com.jwt.backend.domain.note.dto.response.NoteCreateResponseDto;
import com.jwt.backend.domain.note.dto.response.NoteDetailResponseDto;
import com.jwt.backend.domain.note.dto.response.NoteListResponseDto;
import com.jwt.backend.domain.note.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/note")
public class NoteController {

    private final NoteService noteService;

    @PostMapping()
    public ResponseEntity<NoteCreateResponseDto> create(@RequestBody @Valid NoteCreateReqestDto noteCreateReqestDto, Authentication authentication) {
        return noteService.create(noteCreateReqestDto, (Member) authentication.getPrincipal());
    }

    @GetMapping()
    public ResponseEntity<List<NoteListResponseDto>> findList(@PageableDefault(value = 9, sort = "id",
            direction = Sort.Direction.DESC) Pageable pageable, Authentication authentication) {
        return noteService.findList(pageable, (Member) authentication.getPrincipal());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteDetailResponseDto> detail(@PathVariable("id") Long id, Authentication authentication) {
        return noteService.detail(id, (Member) authentication.getPrincipal());
    }

    @DeleteMapping()
    public ResponseEntity<Long> delete(@RequestBody @Valid NoteDeleteRequestDto noteDeleteRequestDto, Authentication authentication) {
        return noteService.delete(noteDeleteRequestDto, (Member) authentication.getPrincipal());
    }

    @PutMapping()
    public ResponseEntity<Long> update(@RequestBody @Valid NoteUpdateRequestDto noteUpdateRequestDto, Authentication authentication) {
        return noteService.update(noteUpdateRequestDto, (Member) authentication.getPrincipal());
    }

}
