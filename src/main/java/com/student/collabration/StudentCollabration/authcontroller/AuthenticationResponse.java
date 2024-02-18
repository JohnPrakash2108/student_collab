package com.student.collabration.StudentCollabration.authcontroller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class AuthenticationResponse {
    private long id;
    private String token;
    private String status;
}

