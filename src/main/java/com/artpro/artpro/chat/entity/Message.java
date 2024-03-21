package com.artpro.artpro.chat.entity;

import com.artpro.artpro.room.entity.ChattingRoom;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sender;
    private String content;
    private LocalDateTime createAt;

    @ManyToOne
    private ChattingRoom chattingRoom;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Builder
    public Message(Long id, String sender, String content, String createAt, ChattingRoom chattingRoom, Type type) {
        this.id = id;
        this.sender = sender;
        this.content = content;
        this.createAt = LocalDateTime.parse(createAt);
        this.chattingRoom = chattingRoom;
        this.type = type;
    }
}
