package com.artpro.artpro.chat.entity;

import com.artpro.artpro.room.entity.ChattingRoom;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sender;
    private String message;
    private LocalDateTime createAt;

    @ManyToOne
    private ChattingRoom chattingRoom;

    @Builder
    public Message(Long id, String sender, String message, LocalDateTime createAt, ChattingRoom chattingRoom) {
        this.id = id;
        this.sender = sender;
        this.message = message;
        this.createAt = createAt;
        this.chattingRoom = chattingRoom;
    }
}
