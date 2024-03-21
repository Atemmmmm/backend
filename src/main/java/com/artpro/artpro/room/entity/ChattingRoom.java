package com.artpro.artpro.room.entity;

import com.artpro.artpro.board.entity.Board;
import com.artpro.artpro.member.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChattingRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Member createBy;

    @ManyToOne
    private Board board;

    @Builder
    public ChattingRoom(Long id, Member createBy, Board board) {
        this.id = id;
        this.createBy = createBy;
        this.board = board;
    }
}
