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
    public ResponseEntity<List<PostIdea>> getUserIdeas(){
        List<PostIdea> userIdeas = service.getUserIdeas();
        return new ResponseEntity<>(userIdeas,HttpStatus.OK);
    }
}
