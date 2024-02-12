package com.student.collabration.StudentCollabration.authcontroller;

import lombok.Data;

@Data
public class ChangePasswordRequest {
    private String oldPassword;
    private String newPassword;
}
