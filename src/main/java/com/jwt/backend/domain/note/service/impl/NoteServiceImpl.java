package com.jwt.backend.domain.note.service.impl;

import com.jwt.backend.domain.member.entity.Member;
import com.jwt.backend.domain.member.repository.MemberRepository;
import com.jwt.backend.domain.note.dto.request.NoteCreateReqestDto;
import com.jwt.backend.domain.note.dto.request.NoteDeleteRequestDto;
import com.jwt.backend.domain.note.dto.request.NoteUpdateRequestDto;
import com.jwt.backend.domain.note.dto.response.NoteCreateResponseDto;
import com.jwt.backend.domain.note.dto.response.NoteDetailResponseDto;
import com.jwt.backend.domain.note.dto.response.NoteListResponseDto;
import com.jwt.backend.domain.note.entity.Note;
import com.jwt.backend.domain.note.repository.NoteRepository;
import com.jwt.backend.domain.note.service.NoteService;
import com.jwt.backend.global.exception.CustomException;
import com.jwt.backend.global.exception.ErrorCode;
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

        Note note = new Note(noteCreateReqestDto);

        Member findMember = findMember(principal.getId());

        note.setMember(findMember);

        noteRepository.save(note);

        NoteCreateResponseDto noteCreateResponseDto = new NoteCreateResponseDto(note);

        log.info("create note ID : {}", note.getId());

        return ResponseEntity.created(null).body(noteCreateResponseDto);
    }

    @Override
    public ResponseEntity<List<NoteListResponseDto>> findList(Pageable pageable, Member principal) {

        Page<Note> findNotePage = noteRepository.findByMemberId(pageable, principal.getId());

        List<NoteListResponseDto> noteList = new ArrayList<>();

        findNotePage.forEach(note -> noteList.add(new NoteListResponseDto(note)));

        log.info("findList note");

        return ResponseEntity.ok(noteList);
    }

    @Override
    public ResponseEntity<NoteDetailResponseDto> detail(Long id, Member principal) {

        Note findNote = findNote(id);

        idMatching(principal, findNote);

        NoteDetailResponseDto noteDetailResponseDto = new NoteDetailResponseDto(findNote);

        log.info("detail note ID : {}", findNote.getId());

        return ResponseEntity.ok(noteDetailResponseDto);
    }

    @Override
    @Transactional
    public ResponseEntity<Long> delete(NoteDeleteRequestDto noteDeleteRequestDto, Member principal) {

        Note findNote = findNote(noteDeleteRequestDto.getId());

        idMatching(principal, findNote);

        noteRepository.deleteById(findNote.getId());

        log.info("delete note ID : {}", findNote.getId());

        return ResponseEntity.ok(findNote.getId());
    }

    @Override
    @Transactional
    public ResponseEntity<Long> update(NoteUpdateRequestDto noteUpdateRequestDto, Member principal) {

        Note findNote = findNote(Long.parseLong(noteUpdateRequestDto.getId()));

        Member findMember = findMember(principal.getId());

        idMatching(findMember, findNote);

        findNote.update(noteUpdateRequestDto);

        log.info("update note ID : {}", findNote.getId());

        return ResponseEntity.ok(findNote.getId());

    }

    @Override
    public ResponseEntity<Long> count(Member principal) {

        Long count = noteRepository.countByMemberId(principal.getId());

        return ResponseEntity.ok(count);
    }

    private void idMatching(Member member, Note note) {
        if (!member.getId().equals(note.getMember().getId())) {
            throw new CustomException(ErrorCode.NOTE_NOT_FOUND);
        }
    }

    private Note findNote(Long id) {
        return noteRepository.findById(id)
                .orElseThrow(()->{
                    throw new CustomException(ErrorCode.NOTE_NOT_FOUND);
                });
    }

    private Member findMember(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(()->{
                    throw new CustomException(ErrorCode.MEMBER_NOT_FOUND);
                });
    }
}
