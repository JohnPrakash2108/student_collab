package com.student.collabration.StudentCollabration.service;

import com.student.collabration.StudentCollabration.dto.CommentDto;
import com.student.collabration.StudentCollabration.dto.UserDto;
import com.student.collabration.StudentCollabration.modal.Comment;
import com.student.collabration.StudentCollabration.modal.PostIdea;
import com.student.collabration.StudentCollabration.repositary.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService {
    private final CommentRepo commentRepo;

    @Autowired
    public CommentService(CommentRepo commentRepo) {
        this.commentRepo = commentRepo;
    }

    public List<CommentDto> getAllComments() {
        List<Comment> comments = commentRepo.findAll();
        return comments.stream()
                .map(comment -> CommentDto.builder()
                        .id(comment.getId())
                        .content(comment.getContent())
                        .createdAt(comment.getCreatedAt())
                        .user(UserDto.builder()
                                .id((int) comment.getUsers().getId())
                                .userName(comment.getUsers().getUName())
                                .firstName(comment.getUsers().getFirstName())
                                .lastName(comment.getUsers().getLastName())
                                .build())
                        .postIdeaId(comment.getPostIdea().getId())
                        .build())
                .collect(Collectors.toList());
    }






//    public CommentDto getCommentById(long id) {
//        Optional<Comment> optionalComment = commentRepo.findById(id);
//        if (optionalComment.isPresent()) {
//            Comment comment = optionalComment.get();
//            return CommentDto.builder()
//                    .id(comment.getId())
//                    .content(comment.getContent())
//                    .createdAt(comment.getCreatedAt())
//                    .users(comment.getUsers().getId())
//                    .postIdeaId(comment.getPostIdea().getId())
//                    .build();
//        }
//        return null;
//    }

//    public Comment saveComment(CommentDto commentDto) {
//        Comment comment = Comment.builder()
//                .content(commentDto.getContent())
//                .createdAt(LocalDateTime.now())
//                .user(Users.builder().id(commentDto.getUserId()).build())
//                .postIdea(PostIdea.builder().id(commentDto.getPostIdeaId()).build())
//                .build();
//        return commentRepo.save(comment);
//    }
}
