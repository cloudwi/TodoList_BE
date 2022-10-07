package com.jwt.backend.domain.note.service;

import com.jwt.backend.domain.member.entity.Member;
import com.jwt.backend.domain.note.dto.request.NoteCreateReqestDto;
import com.jwt.backend.domain.note.dto.request.NoteDeleteRequestDto;
import com.jwt.backend.domain.note.dto.request.NoteUpdateRequestDto;
import com.jwt.backend.domain.note.dto.response.NoteCreateResponseDto;
import com.jwt.backend.domain.note.dto.response.NoteDetailResponseDto;
import com.jwt.backend.domain.note.dto.response.NoteListResponseDto;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NoteService {
    public ResponseEntity<NoteCreateResponseDto> create(NoteCreateReqestDto noteCreateReqestDto, Member principal);

    ResponseEntity<List<NoteListResponseDto>> findList(Pageable pageable, Member principal);

    ResponseEntity<NoteDetailResponseDto> detail(Long id, Member principal);

    ResponseEntity<Long> delete(NoteDeleteRequestDto noteDeleteRequestDto, Member principal);

    ResponseEntity<Long> update(NoteUpdateRequestDto noteUpdateRequestDto, Member principal);

    ResponseEntity<Long> count(Member principal);
}
