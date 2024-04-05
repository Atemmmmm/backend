package com.artpro.artpro.chat.dto;

import com.artpro.artpro.chat.entity.Type;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class MessageRequest {

    private Type type;
    private String message;
    private final String createAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
    private String senderEmail;
    private String imageByteCode;
    private String fileName;
    private String fileExtension;
}
