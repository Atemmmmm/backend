package com.artpro.artpro.heart.entity;

import com.artpro.artpro.board.entity.Board;
import com.artpro.artpro.member.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Heart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean isValid;

    @CreatedDate
    private LocalDateTime createAt;
    private LocalDateTime deleteAt;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Heart(Long id, LocalDateTime createAt, LocalDateTime deleteAt, Board board, Member member) {
        this.id = id;
        this.isValid = true;
        this.createAt = createAt;
        this.deleteAt = deleteAt;
        this.board = board;
        this.member = member;
    }

    public void delete() {
        this.isValid = false;
    }

    public boolean isValid() {
        return isValid;
    }
}