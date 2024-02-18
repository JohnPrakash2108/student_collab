package com.student.collabration.StudentCollabration.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDto {
    private long id;
    private String content;
    private LocalDateTime createdAt;
    @JsonIgnore
    private Long userId;
    private UserDto user;
    private long postIdeaId;
}
