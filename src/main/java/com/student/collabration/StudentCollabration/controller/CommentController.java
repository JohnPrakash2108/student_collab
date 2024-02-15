package com.student.collabration.StudentCollabration.controller;

import com.student.collabration.StudentCollabration.dto.CommentDto;
import com.student.collabration.StudentCollabration.modal.Comment;
import com.student.collabration.StudentCollabration.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/get-comments")
    public ResponseEntity<List<CommentDto>> getAllComments() {
        List<CommentDto> commentDtos = commentService.getAllComments();
        return new ResponseEntity<>(commentDtos, HttpStatus.OK);
    }

//    @GetMapping("/{id}")ty<CommentDto> getCommentById(@PathVariable long id) {
////        CommentDto comment
//    public ResponseEntiDto = commentService.getCommentById(id);
//        return new ResponseEntity<>(commentDto, HttpStatus.OK);
//    }
//
//    @PostMapping("/save-comment")
//    public ResponseEntity<?> saveComment(@RequestBody CommentDto commentDto) {
//        Comment savedCommentDto = commentService.saveComment(commentDto);
//        return null;
//    }

}
