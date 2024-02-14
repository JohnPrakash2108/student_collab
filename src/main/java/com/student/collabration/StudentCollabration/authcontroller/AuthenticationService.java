package com.student.collabration.StudentCollabration.authcontroller;

import com.student.collabration.StudentCollabration.configuration.JwtService;
import com.student.collabration.StudentCollabration.modal.Users;
import com.student.collabration.StudentCollabration.repositary.UserRepository;
import com.student.collabration.StudentCollabration.configuration.JwtService;
import com.student.collabration.StudentCollabration.repositary.UserRepository;
import freemarker.core.ParseException;
import freemarker.template.*;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Configuration freemarkerConfig;

    public AuthenticationResponse register(RegisterRequest request) {
        // Generate OTP
        String otp = generateOTP();

        // Send OTP to user's email
        sendOTP(request.getEmail(), otp,request.getFirstName()+" "+request.getLastName());
        var user = Users.builder()
                .email(request.getEmail())
                .userName(request.getUserName())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .password(passwordEncoder.encode(request.getPassword()))
                .otp(otp)
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        // Check if the request contains email or username
        String emailOrUsername = request.getEmailOrUsername();

        // Find user by email or username
        var user = repository.findByEmailOrUserName(emailOrUsername, emailOrUsername)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email/username: " + emailOrUsername));


            // Authenticate the user
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getEmail(),
                            request.getPassword()
                    )
            );

            // Generate JWT token
            var jwtToken = jwtService.generateToken(user);

            // Return authentication response
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .status(user.getOtp().equals("0")?"Validated":"Not Validated")
                    .build();




    }

    public void changePassword(ChangePasswordRequest request) {
        // Get the current user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        Users currentUser = repository.findByEmailOrUserName(currentUsername, currentUsername)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username/email: " + currentUsername));

        // Verify the old password
        if (!passwordEncoder.matches(request.getOldPassword(), currentUser.getPassword())) {
            throw new IllegalArgumentException("Old password is incorrect");
        }

        // Update the password
        currentUser.setPassword(passwordEncoder.encode(request.getNewPassword()));
        repository.save(currentUser);
    }

    private String generateOTP() {
        // Generate a random 6-digit OTP
        Random random = new Random();
        return String.format("%06d", random.nextInt(1000000));
    }

    public void sendOTP(String email, String otp,String name) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(email);
            helper.setSubject("OTP for Verification");

            // Load FreeMarker template
            Template template = freemarkerConfig.getTemplate("email.html");

            // Create a data model for the template
            Map<String, Object> model = new HashMap<>();
            model.put("otp", otp);
            model.put("name",name);

            // Process the template with the data model
            StringWriter stringWriter = new StringWriter();
            template.process(model, stringWriter);

            // Set the email body
            helper.setText(stringWriter.toString(), true);

            // Send the email
            javaMailSender.send(message);
        } catch (IOException | TemplateException | MessagingException e) {
            throw new RuntimeException("Error sending OTP email", e);
        }
    }

    public boolean validateOTP(String email, String enteredOTP) {
        // Retrieve the user from the database
        Users user = repository.findByEmailOrUserName(email,email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        // Retrieve the saved OTP
        String savedOTP = user.getOtp();

        // Compare the entered OTP with the saved OTP
         if(enteredOTP.equals(savedOTP)){
             user.setOtp("0");
             repository.save(user);
            return true;
        }
         return false;
    }

    public String resendOTP(String email) {
        String otp = generateOTP();

        var user = repository.findByEmailOrUserName(email, email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email/username: " + email));
        user.setOtp(otp);
        repository.save(user);
        sendOTP(user.getEmail(),otp,user.getFirstName()+user.getLastName());
        return "Sent";
    }
}