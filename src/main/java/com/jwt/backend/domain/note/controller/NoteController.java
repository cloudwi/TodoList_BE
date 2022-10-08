package com.jwt.backend.domain.note.controller;

import com.jwt.backend.domain.member.entity.Member;
import com.jwt.backend.domain.note.dto.request.NoteCreateReqestDto;
import com.jwt.backend.domain.note.dto.request.NoteDeleteRequestDto;
import com.jwt.backend.domain.note.dto.request.NoteUpdateRequestDto;
import com.jwt.backend.domain.note.dto.response.NoteCreateResponseDto;
import com.jwt.backend.domain.note.dto.response.NoteDetailResponseDto;
import com.jwt.backend.domain.note.dto.response.NoteListResponseDto;
import com.jwt.backend.domain.note.service.NoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Note 컨트롤러")
@RestController
@RequiredArgsConstructor
@RequestMapping("/note")
public class NoteController {

    private final NoteService noteService;

    @Operation(summary = "Note 생성",security = {})
    @PostMapping()
    public ResponseEntity<NoteCreateResponseDto> create(@RequestBody @Valid NoteCreateReqestDto noteCreateReqestDto, Authentication authentication) {
        return noteService.create(noteCreateReqestDto, (Member) authentication.getPrincipal());
    }

    @Operation(summary = "해당 회원이 작성한 NoteList 조회 하기")
    @GetMapping()
    public ResponseEntity<List<NoteListResponseDto>> findList(@PageableDefault(value = 9, sort = "id",
            direction = Sort.Direction.DESC) Pageable pageable, Authentication authentication) {
        return noteService.findList(pageable, (Member) authentication.getPrincipal());
    }

    @Operation(summary = "해당 회원이 작성한 Note 상세 조회 하기")
    @GetMapping("/{id}")
    public ResponseEntity<NoteDetailResponseDto> detail(@PathVariable("id") Long id, Authentication authentication) {
        return noteService.detail(id, (Member) authentication.getPrincipal());
    }

    @Operation(summary = "해당 회원이 작성한 Note 삭제 하기")
    @DeleteMapping()
    public ResponseEntity<Long> delete(@RequestBody @Valid NoteDeleteRequestDto noteDeleteRequestDto, Authentication authentication) {
        return noteService.delete(noteDeleteRequestDto, (Member) authentication.getPrincipal());
    }

    @Operation(summary = "해당 회원이 작성한 Note 수정 하기")
    @PutMapping()
    public ResponseEntity<Long> update(@RequestBody @Valid NoteUpdateRequestDto noteUpdateRequestDto, Authentication authentication) {
        return noteService.update(noteUpdateRequestDto, (Member) authentication.getPrincipal());
    }

    @Operation(summary = "해당 회원이 작성한 Note 갯수 응답")
    @GetMapping("/count")
    public ResponseEntity<Long> count(Authentication authentication) {
        return noteService.count((Member) authentication.getPrincipal());
    }

}
