package com.student.collabration.StudentCollabration.dto;

import com.student.collabration.StudentCollabration.modal.PostIdea;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostLikeId implements Serializable {

    private Long post;
    private Long user;

    public void setPostIdea(PostIdea post) {
    }

    // constructors, getters and setters
}

