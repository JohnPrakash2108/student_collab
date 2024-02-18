package com.student.collabration.StudentCollabration.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PostLikesDto {

    private long id;
    private LocalDateTime createdAt;
    private long rating;

    private PostIdeaDto postIdeaDto;
    private UserDto userDto;
}
