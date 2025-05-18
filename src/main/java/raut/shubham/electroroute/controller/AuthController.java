package raut.shubham.electroroute.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import raut.shubham.electroroute.error.EmailAlreadyUseException;
import raut.shubham.electroroute.entity.User;
import raut.shubham.electroroute.entity.dto.SignInRequest;
import raut.shubham.electroroute.entity.dto.SignInResponse;
import raut.shubham.electroroute.entity.dto.SignUpResponse;
import raut.shubham.electroroute.entity.dto.SignupRequest;
import raut.shubham.electroroute.repository.UserRepository;
import raut.shubham.electroroute.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    private ResponseEntity<SignUpResponse> registerUser(@RequestBody SignupRequest signupRequest) throws EmailAlreadyUseException {
        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            throw new EmailAlreadyUseException("Email is already in use!");
        }
        return ResponseEntity.ok(new SignUpResponse(userService.registerUser(signupRequest)));
    }

    @PostMapping("/login")
    private ResponseEntity<?> signInUser(@RequestBody SignInRequest signInRequest) {
        User user = userRepository.findByEmail(signInRequest.getEmail());
        if(user==null) return  ResponseEntity.badRequest().body("User not exits");
        if (userService.verifyUser(user, signInRequest)) {
            return ResponseEntity.ok(new SignInResponse(user));
        }
        return ResponseEntity.badRequest().body("Login Unsuccessful");
    }
}