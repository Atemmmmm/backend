package com.artpro.artpro.chat.entity;

import com.artpro.artpro.room.entity.ChattingRoom;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sender;
    private String content;
    private LocalDateTime createAt;

    @ManyToOne
    private ChattingRoom chattingRoom;

    @Builder
    public Message(Long id, String sender, String content, LocalDateTime createAt, ChattingRoom chattingRoom) {
        this.id = id;
        this.sender = sender;
        this.content = content;
        this.createAt = createAt;
        this.chattingRoom = chattingRoom;
    }
}
