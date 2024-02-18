package com.student.collabration.StudentCollabration.service;

import com.student.collabration.StudentCollabration.dto.PostIdeaDto;
import com.student.collabration.StudentCollabration.dto.UserDto;
import com.student.collabration.StudentCollabration.modal.PostIdea;
import com.student.collabration.StudentCollabration.modal.Users;
import com.student.collabration.StudentCollabration.repositary.PostIdeaRepository;
import com.student.collabration.StudentCollabration.repositary.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class PostIdeaService {
    private final ModelMapper modelMapper;

    @Autowired
    private PostIdeaRepository postIdeaRepository;
    @Autowired
    private UserRepository userRepository;

    public PostIdeaService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public void createPost(PostIdeaDto postIdeaDto) {
        // Map the PostIdeaDto to PostIdea
        postIdeaDto.setId(0);
        postIdeaDto.setLikeCount(0);
        String trimmedContent = postIdeaDto.getContent().trim();
        postIdeaDto.setContent(trimmedContent);
        PostIdea postRating = modelMapper.map(postIdeaDto, PostIdea.class);
        postRating.setDeleted(false);
        // Setting createdAt and updatedAt timestamps
        LocalDateTime currentTime = LocalDateTime.now();
        postRating.setCreatedAt(currentTime);
        postRating.setUpdatedAt(currentTime);
        Users user = getCurrentUser();

        try {
            postRating.setUser(user);
            // Save the PostIdea to DB
            postIdeaRepository.save(postRating);
        } catch (UsernameNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public void updatePost(PostIdeaDto postIdeaDto) {
        // Retrieve the existing post idea from the database
        PostIdea postRating = postIdeaRepository.findById(postIdeaDto.getId())
                .orElseThrow(() -> new NoSuchElementException("Post idea not found with id: " + postIdeaDto.getId()));

        // Map the updated values from the DTO to the retrieved entity
        modelMapper.map(postIdeaDto, postRating);

        // Update the 'updatedAt' timestamp
        postRating.setUpdatedAt(LocalDateTime.now());

        // Save the updated post idea back to the database
        postIdeaRepository.save(postRating);
    }

    public List<PostIdeaDto> getUserIdeas() {
        List<PostIdea> userIdeas = new ArrayList<>();
        try {
            Users user = getCurrentUser();
            userIdeas = postIdeaRepository.findByUserAndDeletedOrderByCreatedAtDesc(user, false);
        } catch (UsernameNotFoundException ex) {
            ex.printStackTrace();
        }

        return userIdeas.stream()
                .map(postIdea -> {
                    PostIdeaDto postIdeaDto = modelMapper.map(postIdea, PostIdeaDto.class);
                    UserDto userDto = modelMapper.map(postIdea.getUser(), UserDto.class);
                    postIdeaDto.setUserDto(userDto);
                    return postIdeaDto;
                })
                .collect(Collectors.toList());
    }


    public Users getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        Users user = userRepository.findByEmailOrUserName(currentUsername, currentUsername)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username/email: " + currentUsername));
        return user;
    }

    public void deletePost(long id) {
        Optional<PostIdea> optionalPostIdea = postIdeaRepository.findById(id);

        // If the PostIdea exists, set its deleted field to true and save it
        optionalPostIdea.ifPresent(postIdea -> {
            postIdea.setDeleted(true);
            postIdeaRepository.save(postIdea);
        });

    }

    public List<PostIdeaDto> getAllPosts() {
        List<PostIdea> userIdeas = new ArrayList<>();
        try {
            userIdeas = postIdeaRepository.findAllByDeletedFalseOrderByCreatedAtDesc();

        } catch (UsernameNotFoundException ex) {
            ex.printStackTrace();
        }

        return userIdeas.stream()
                .map(postIdea -> {
                    PostIdeaDto postIdeaDto = modelMapper.map(postIdea, PostIdeaDto.class);
                    UserDto userDto = modelMapper.map(postIdea.getUser(), UserDto.class);
                    postIdeaDto.setUserDto(userDto);
                    return postIdeaDto;
                })
                .collect(Collectors.toList());
    }

    public List<PostIdeaDto> getAllPostsById(long id){
        Optional<PostIdea> postIdeaOptional = postIdeaRepository.findById(id);

        if (postIdeaOptional.isPresent()) {
            PostIdea postIdea = postIdeaOptional.get();
            PostIdeaDto postIdeaDto = modelMapper.map(postIdea, PostIdeaDto.class);
            UserDto userDto = modelMapper.map(postIdea.getUser(), UserDto.class);
            postIdeaDto.setUserDto(userDto);
            return Collections.singletonList(postIdeaDto);
        } else {
            // If the post doesn't exist, you can return an empty list or throw an exception
            return Collections.emptyList();
        }
    }

}
