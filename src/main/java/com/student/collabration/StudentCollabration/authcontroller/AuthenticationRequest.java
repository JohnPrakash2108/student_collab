package com.student.collabration.StudentCollabration.authcontroller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

    private String userName;
    String password;

    public String getEmailOrUsername() {
        return userName;
    }
}

