package com.artpro.artpro.room.entity;

import com.artpro.artpro.board.entity.Board;
import com.artpro.artpro.member.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Getter
@DynamicInsert
@SQLRestriction(value = "status = 'CREATED'")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChattingRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Member createBy;

    @ManyToOne
    private Board board;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'CREATED'")
    private RoomStatus status;

    @Builder
    public ChattingRoom(Long id, Member createBy, Board board, RoomStatus status) {
        this.id = id;
        this.createBy = createBy;
        this.board = board;
        this.status = status;
    }

    public Member findCounterpart(Member member) {
        if (member.isSameMember(this.createBy)) {
            return this.board.getMember();
        }
        return this.createBy;
    }

    public boolean isParticipants(String senderEmail) {
        return createBy.isSameMember(senderEmail) || board.getMember().isSameMember(senderEmail);
    }

    public void delete() {
        this.status = RoomStatus.DELETED;
    }
}
