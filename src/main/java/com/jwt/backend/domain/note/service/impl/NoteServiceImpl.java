package com.jwt.backend.domain.note.service.impl;

import com.jwt.backend.domain.member.entity.Member;
import com.jwt.backend.domain.member.exception.MemberException;
import com.jwt.backend.domain.member.exception.MemberExceptionType;
import com.jwt.backend.domain.member.repository.MemberRepository;
import com.jwt.backend.domain.note.dto.request.NoteCreateReqestDto;
import com.jwt.backend.domain.note.dto.request.NoteDetailRequestDto;
import com.jwt.backend.domain.note.dto.response.NoteCreateResponseDto;
import com.jwt.backend.domain.note.dto.response.NoteDetailResponseDto;
import com.jwt.backend.domain.note.dto.response.NoteListResponseDto;
import com.jwt.backend.domain.note.entity.Note;
import com.jwt.backend.domain.note.exception.NoteException;
import com.jwt.backend.domain.note.exception.NoteExceptionType;
import com.jwt.backend.domain.note.repository.NoteRepository;
import com.jwt.backend.domain.note.service.NoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public ResponseEntity<List<NoteListResponseDto>> findList(Pageable pageable, Member principal) {
        Member member = memberRepository.findById(principal.getId())
                .orElseThrow(()->{
                    throw new MemberException(MemberExceptionType.NOT_SIGNUP_EMAIL);
                });

        Page<Note> notePage = noteRepository.findByMemberId(pageable, member.getId());

        List<NoteListResponseDto> noteList = new ArrayList<>();

        notePage.forEach(note -> {
            noteList.add(new NoteListResponseDto(note));
        });

        return ResponseEntity.ok(noteList);
    }

    @Override
    public ResponseEntity<NoteDetailResponseDto> detail(NoteDetailRequestDto noteDetailRequestDto, Member principal) {
        Note note = noteRepository.findById(noteDetailRequestDto.getId())
                .orElseThrow(()->{
                    throw new NoteException(NoteExceptionType.NOT_FOUND_NOTE);
                });

        if (note.getMember().getId() != principal.getId()) {
            throw new NoteException(NoteExceptionType.NOT_MATCHING_NOTE);
        }

        NoteDetailResponseDto noteDetailResponseDto = new NoteDetailResponseDto(note);

        return ResponseEntity.ok(noteDetailResponseDto);
    }
}
