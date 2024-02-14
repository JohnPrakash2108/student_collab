package com.student.collabration.StudentCollabration.controller;

import com.student.collabration.StudentCollabration.service.EmailService;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/sendEmail")
    public String sendEmail() {
        try {
            Map<String, Object> model = new HashMap<>();
            model.put("name", "Krishna Kesav");
            model.put("otp", "98123");
            emailService.sendEmail("krishnakesav143@gmail.com", "Testing EDUTECH - JOHN", "email.html", model);
            return "Email sent successfully!";
        } catch (MessagingException | IOException | TemplateException e) {
            return "Error sending email: " + e.getMessage();
        }
    }
}
