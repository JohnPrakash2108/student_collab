//package com.student.collabration.StudentCollabration.service;
//
//import com.student.collabration.StudentCollabration.dto.PostLikeId;
//import com.student.collabration.StudentCollabration.modal.PostIdea;
//import com.student.collabration.StudentCollabration.modal.PostLikes;
//import com.student.collabration.StudentCollabration.modal.Users;
//import com.student.collabration.StudentCollabration.repositary.PostIdeaRepository;
//import com.student.collabration.StudentCollabration.repositary.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class PostService {
//
//    private final PostIdeaRepository postRepository;
//    private final PostIdeaRepository postLikeRepository;
//    private final UserRepository userRepository;
//
//    @Autowired
//    public PostService(PostIdeaRepository postRepository, PostIdeaRepository postLikeRepository,
//                       UserRepository userRepository) {
//        this.postRepository = postRepository;
//        this.postLikeRepository = postLikeRepository;
//        this.userRepository = userRepository;
//    }
//
//
//
//    public void likePost(Long postId, Long userId) {
//        Optional<PostIdea> optionalPost = postRepository.findById(postId);
//        Optional<Users> optionalUser = userRepository.findById(userId);
//
//        // Check if both the post and user exist
//        if (optionalPost.isPresent() && optionalUser.isPresent()) {
//            PostIdea post = optionalPost.get();
//            Users user = optionalUser.get();
//
//            // Check if the user has already liked the post
//            if (!postLikeRepository.existsById(new PostLikeId(postId, userId))) {
//                // Increment the likes count
//                post.setLikeCount(post.getLikeCount() + 1);
//                postRepository.save(post);
//
//                // Record the user's like
//                PostLikes postLikes = new PostLikes();
//                postLikes.setPostIdea(post);
//                postLikes.setUser(user);
//            }
//        }
//    }
//
//
//
//}
//
//
//}