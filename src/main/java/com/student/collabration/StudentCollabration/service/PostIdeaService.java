package com.student.collabration.StudentCollabration.service;

import com.student.collabration.StudentCollabration.dto.PostIdeaDto;
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
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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
        PostIdea postIdea = modelMapper.map(postIdeaDto, PostIdea.class);

        // Setting createdAt and updatedAt timestamps
        LocalDateTime currentTime = LocalDateTime.now();
        postIdea.setCreatedAt(currentTime);
        postIdea.setUpdatedAt(currentTime);
        Users user = getCurrentUser();

        try {


            postIdea.setUser(user);

            // Save the PostIdea to DB
            postIdeaRepository.save(postIdea);
        } catch (UsernameNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public void updatePost(PostIdeaDto postIdeaDto) {
        // Retrieve the existing post idea from the database
        PostIdea postIdea = postIdeaRepository.findById(postIdeaDto.getId())
                .orElseThrow(() -> new NoSuchElementException("Post idea not found with id: " + postIdeaDto.getId()));

        // Map the updated values from the DTO to the retrieved entity
        modelMapper.map(postIdeaDto, postIdea);

        // Update the 'updatedAt' timestamp
        postIdea.setUpdatedAt(LocalDateTime.now());

        // Save the updated post idea back to the database
        postIdeaRepository.save(postIdea);
    }

    public List<PostIdea> getUserIdeas() {
        List<PostIdea> userIdeas = new ArrayList<>();



            try {
               Users user = getCurrentUser();
                userIdeas = postIdeaRepository.findByUser(user);
            } catch (UsernameNotFoundException ex) {
                ex.printStackTrace();
            }

        return userIdeas;
    }

    public Users getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        Users user = userRepository.findByEmailOrUserName(currentUsername, currentUsername)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username/email: " + currentUsername));
        return user;
    }
}
