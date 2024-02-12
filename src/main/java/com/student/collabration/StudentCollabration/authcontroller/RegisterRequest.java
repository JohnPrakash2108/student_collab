package com.student.collabration.StudentCollabration.authcontroller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String userName;
    private String name;
    private String email;
    private String password;
    private String firstName;
    private String lastName;

}
