package com.jwt.backend.domain.note.service.impl;

import com.jwt.backend.domain.member.entity.Member;
import com.jwt.backend.domain.member.exception.MemberException;
import com.jwt.backend.domain.member.exception.MemberExceptionType;
import com.jwt.backend.domain.member.repository.MemberRepository;
import com.jwt.backend.domain.note.dto.request.NoteCreateReqestDto;
import com.jwt.backend.domain.note.dto.response.NoteCreateResponseDto;
import com.jwt.backend.domain.note.entity.Note;
import com.jwt.backend.domain.note.repository.NoteRepository;
import com.jwt.backend.domain.note.service.NoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public ResponseEntity<NoteCreateResponseDto> create(NoteCreateReqestDto noteCreateReqestDto, Member principal) {
        Note note = noteCreateReqestDto.toEntity();
        Member member = memberRepository.findById(principal.getId())
                .orElseThrow(()->{
                    throw new MemberException(MemberExceptionType.NOT_SIGNUP_EMAIL);
                });

        note.setMember(member);

        noteRepository.save(note);

        NoteCreateResponseDto noteCreateResponseDto = new NoteCreateResponseDto(note);

        return ResponseEntity.created(null).body(noteCreateResponseDto);
    }
}
