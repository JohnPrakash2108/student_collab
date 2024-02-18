package com.student.collabration.StudentCollabration.controller;

import com.student.collabration.StudentCollabration.dto.PostIdeaDto;
import com.student.collabration.StudentCollabration.modal.PostIdea;
import com.student.collabration.StudentCollabration.service.PostIdeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostIdeaController {
    @Autowired
    private PostIdeaService service;
    @PostMapping("/create-post")
    public ResponseEntity<String> createIdea(@RequestBody PostIdeaDto postIdea){
        service.createPost(postIdea);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/edit-post")
    public ResponseEntity<?> updatePost(@RequestBody PostIdeaDto postIdeaDto){
        service.updatePost(postIdeaDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/get-posts")
    public ResponseEntity<List<PostIdeaDto>> getUserIdeas(){
        List<PostIdeaDto> userIdeas = service.getUserIdeas();
        return new ResponseEntity<>(userIdeas,HttpStatus.OK);
    }

    @DeleteMapping("/delete-post/{id}")
    public ResponseEntity<?> deletePost(@PathVariable("id") long id){
        service.deletePost(id);
        return new ResponseEntity<>("Deleted",HttpStatus.NO_CONTENT);
    }

    @GetMapping("/get-all-posts")
    public ResponseEntity<List<PostIdeaDto>> getAllIdeas(){
        List<PostIdeaDto> userIdeas = service.getAllPosts();
        return new ResponseEntity<>(userIdeas,HttpStatus.OK);
    }

    @GetMapping("/getUserPosts/{id}")
    public ResponseEntity<List<PostIdeaDto>> getUserPosts(@PathVariable("id") long userId){
        List<PostIdeaDto> userIdeas = service.getAllPostsById(userId);
        return new ResponseEntity<>(userIdeas,HttpStatus.OK);
    }
}
