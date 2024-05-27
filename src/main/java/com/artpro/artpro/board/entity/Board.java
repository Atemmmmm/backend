package com.artpro.artpro.board.entity;

import com.artpro.artpro.member.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLRestriction(value = "status != 'DELETED'")
@DynamicInsert
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String cover;
    private String song;

    private int likeCount;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @CreatedDate
    private LocalDateTime createAt;
    @LastModifiedDate
    private LocalDateTime upDateAt;
    private LocalDateTime deleteAt;
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'CREATED'")
    private BoardStatus status;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    @Enumerated(EnumType.STRING)
    private Category category;

    @Builder
    public Board(Long id, String title, String genre, String cover, String song, Category category, Member member, LocalDateTime createAt, LocalDateTime upDateAt, LocalDateTime deleteAt, BoardStatus status) {
        this.id = id;
        this.title = title;
        this.genre = Genre.getGenre(genre);
        this.cover = cover;
        this.song = song;
        this.category = category;
        this.likeCount = 0;
        this.member = member;
        this.createAt = createAt;
        this.upDateAt = upDateAt;
        this.deleteAt = deleteAt;
        this.status = status;
    }

    public void update(String title, String genre, String cover, String song) {
        this.title = title;
        this.cover = cover;
        this.song = song;
        this.genre = Genre.getGenre(genre);
        this.status = BoardStatus.UPDATED;
    }

    public void updateLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public void delete() {
        this.status = BoardStatus.DELETED;
        this.deleteAt = LocalDateTime.now();
    }
}
