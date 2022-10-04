package com.jwt.backend.domain.note.entity;

import com.jwt.backend.domain.member.entity.Member;
import com.jwt.backend.domain.note.dto.request.NoteCreateReqestDto;
import com.jwt.backend.domain.note.dto.request.NoteUpdateRequestDto;
import com.jwt.backend.domain.note.dto.response.NoteListResponseDto;
import com.jwt.backend.global.entity.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "note")
@Slf4j
public class Note extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "note_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String importance;

    public void setMember(Member member) {
        if (this.member != null) {
            this.member.getNoteList().remove(this);
        }
        this.member = member;
        member.getNoteList().add(this);
    }

    public Note(NoteCreateReqestDto noteCreateReqestDto) {
        this.title = noteCreateReqestDto.getTitle();
        this.content = noteCreateReqestDto.getContent();
        this.importance = noteCreateReqestDto.getImportance();

    }

    public void update(NoteUpdateRequestDto noteUpdateRequestDto) {
        this.title = noteUpdateRequestDto.getTitle();
        this.content = noteUpdateRequestDto.getContent();
        this.importance = noteUpdateRequestDto.getImportance();
    }
}
