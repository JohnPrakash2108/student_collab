package com.student.collabration.StudentCollabration.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostIdeaDto {

    private long id;
    private String content;
    private String category;
    private long likeCount;
    private LocalDateTime createdAt;

    private UserDto userDto;
}
