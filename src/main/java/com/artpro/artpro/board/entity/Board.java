package com.artpro.artpro.board.entity;

import com.artpro.artpro.member.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String cover;
    private String song;
    private String category;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @CreatedDate
    private LocalDateTime createAt;
    @LastModifiedDate
    private LocalDateTime upDateAt;
    private LocalDateTime deleteAt;
    @Enumerated(EnumType.STRING)
    private BoardStatus status;

    @Builder
    public Board(Long id, String title, String cover, String song, Member member, LocalDateTime createAt, LocalDateTime upDateAt, LocalDateTime deleteAt, BoardStatus status) {
        this.id = id;
        this.title = title;
        this.cover = cover;
        this.song = song;
        this.member = member;
        this.createAt = createAt;
        this.upDateAt = upDateAt;
        this.deleteAt = deleteAt;
        this.status = status;
        this.category = member.getRole().toString();
    }
}
