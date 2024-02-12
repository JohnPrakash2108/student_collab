package com.student.collabration.StudentCollabration.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private int id;

    private String userName;
    private String firstName;
    private String lastName;
    @JsonIgnore
    private String email;
    @JsonIgnore
    private String password;
}