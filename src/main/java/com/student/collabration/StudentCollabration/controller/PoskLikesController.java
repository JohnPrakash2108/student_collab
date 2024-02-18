//package com.student.collabration.StudentCollabration.controller;
//
//import com.student.collabration.StudentCollabration.dto.PostLikesDto;
//import com.student.collabration.StudentCollabration.service.PostLikesService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//public class PoskLikesController {
//    @Autowired
//    private PostLikesService postLikesService;
//    @PutMapping("/{postId}/likes")
//    public ResponseEntity<?> updateLikesCount(@PathVariable Long postId, @RequestBody PostLikesDto postLikesDto) {
//        if (postLikesDto.getPostIdeaDto().getId() != postId) {
//            return ResponseEntity.badRequest().build();
//        }
//
//        boolean updated = postLikesService.updateLikesCount(postLikesDto);
//        if (updated) {
//            return ResponseEntity.ok().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//}
