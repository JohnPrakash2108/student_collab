package com.student.collabration.StudentCollabration.service;

import com.student.collabration.StudentCollabration.dto.UserDto;
import com.student.collabration.StudentCollabration.modal.Users;
import com.student.collabration.StudentCollabration.repositary.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsersService {
    public UsersService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }
    @Autowired
    private UserRepository userRepository;


    private final ModelMapper modelMapper;



    public List<UserDto> getUsers(){
        String currentUsername = getCurrentUsername();
        List<Users> users= userRepository.findAll();
        return users.stream()
                .filter(user -> !user.getUsername().equals(currentUsername))
                .map(user -> modelMapper.map(user, UserDto.class)
        ).collect(Collectors.toList());
    }

    private String getCurrentUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return null;
        }
    }
}
