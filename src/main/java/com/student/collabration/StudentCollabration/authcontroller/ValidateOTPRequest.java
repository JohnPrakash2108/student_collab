package com.student.collabration.StudentCollabration.authcontroller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidateOTPRequest {
    private String email;
    private String otp;
}
