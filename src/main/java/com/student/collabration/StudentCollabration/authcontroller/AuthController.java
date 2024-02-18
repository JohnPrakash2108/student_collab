package com.student.collabration.StudentCollabration.authcontroller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService service;

    @PostMapping("/email-verify")
    public ResponseEntity<?> emailVerify(@RequestBody EmailRequest emailRequest){
        service.verifyEmail(emailRequest);
        return null;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        AuthenticationResponse response = service.register(request);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + response.getToken());

        return ResponseEntity.ok().headers(headers).body(response);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        AuthenticationResponse response = service.authenticate(request);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + response.getToken());
        headers.add("Validate",response.getStatus());
        return ResponseEntity.ok().headers(headers).body(response);
    }

    @PostMapping("/change-password")
    public ResponseEntity<Void> changePassword(@RequestBody ChangePasswordRequest request) {
        service.changePassword(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/validate-otp")
    public ResponseEntity<?> validateOTP(@RequestBody ValidateOTPRequest request) {
        String email = request.getEmail();
        String enteredOTP = request.getOtp();

        boolean isValid = service.validateOTP(email, enteredOTP);

        if (isValid) {
            return ResponseEntity.ok("OTP validation successful");
        } else {
            return ResponseEntity.badRequest().body("Invalid OTP");
        }
    }

    @PostMapping("/resend-otp")
    public String resendOTP(@RequestBody ValidateOTPRequest request){
        request.setOtp("0");
        String msg = service.resendOTP(request.getEmail());
        return msg;
    }
}
