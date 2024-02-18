package com.student.collabration.StudentCollabration.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatDto {
    private long id;
    private String message;
    private LocalDateTime createdAt;
    private long senderId;
    private long receiverId;
}
