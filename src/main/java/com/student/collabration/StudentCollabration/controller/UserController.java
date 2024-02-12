package com.student.collabration.StudentCollabration.controller;

import com.student.collabration.StudentCollabration.dto.UserDto;
import com.student.collabration.StudentCollabration.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UsersService service;
    @GetMapping("/getchats")
    public List<UserDto> getUsers(){
        return service.getUsers();
    }
}
