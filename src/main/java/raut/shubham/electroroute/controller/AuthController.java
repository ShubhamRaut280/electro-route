package raut.shubham.electroroute.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import raut.shubham.electroroute.entity.RefreshToken;
import raut.shubham.electroroute.entity.dto.UserInfoDto;
import raut.shubham.electroroute.entity.dto.request.AuthRequestDTO;
import raut.shubham.electroroute.entity.dto.request.RefreshTokenRequestDTO;
import raut.shubham.electroroute.entity.dto.response.JwtResponseDTO;
import raut.shubham.electroroute.service.JwtService;
import raut.shubham.electroroute.service.RefreshTokenService;
import raut.shubham.electroroute.service.UserDetailsServiceImpl;

import java.util.Optional;

import static raut.shubham.electroroute.Utils.ValidationUtils.isValidEmail;
import static raut.shubham.electroroute.Utils.ValidationUtils.isValidPassword;

@Tag(name = "Authentication", description = "Handles user sign-up, login, and JWT token refresh")
@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Operation(summary = "Register a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User registered successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input or user already exists"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody UserInfoDto userInfoDto) {
        if (userInfoDto.getUsername() == null || userInfoDto.getUsername().isEmpty()) {
            return new ResponseEntity<>("Email cannot be empty", HttpStatus.BAD_REQUEST);
        } else if (!isValidEmail(userInfoDto.getUsername())) {
            return new ResponseEntity<>("Invalid email", HttpStatus.BAD_REQUEST);
        }

        if (userInfoDto.getPassword() == null || userInfoDto.getPassword().isEmpty()) {
            return new ResponseEntity<>("Password cannot be empty", HttpStatus.BAD_REQUEST);
        } else if (!isValidPassword(userInfoDto.getPassword())) {
            return new ResponseEntity<>(
                    "Password should have at least 8 characters, one digit, one lowercase, one uppercase, and one special character",
                    HttpStatus.BAD_REQUEST
            );
        }

        try {
            Boolean isSignedUp = userDetailsService.signupUser(userInfoDto);
            if (Boolean.FALSE.equals(isSignedUp)) {
                return new ResponseEntity<>("User already exists", HttpStatus.BAD_REQUEST);
            }
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(userInfoDto.getUsername());
            String jwtToken = jwtService.GenerateToken(userInfoDto.getUsername());
            return new ResponseEntity<>(new JwtResponseDTO(jwtToken, refreshToken.getToken()), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Authenticate user and generate access and refresh tokens")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authentication successful"),
            @ApiResponse(responseCode = "401", description = "Invalid credentials"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/login")
    public ResponseEntity<?> authenticateAndGetToken(@RequestBody AuthRequestDTO authRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword())
        );
        if (authentication.isAuthenticated()) {
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(authRequestDTO.getUsername());
            return new ResponseEntity<>(
                    JwtResponseDTO.builder()
                            .accessToken(jwtService.GenerateToken(authRequestDTO.getUsername()))
                            .token(refreshToken.getToken())
                            .build(),
                    HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>("Authentication failed", HttpStatus.UNAUTHORIZED);
        }
    }

    @Operation(summary = "Refresh access token using a valid refresh token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Token refreshed successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid or expired refresh token"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/refreshToken")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequestDTO refreshTokenRequestDTO) {
        Optional<RefreshToken> refreshToken = refreshTokenService.findByToken(refreshTokenRequestDTO.getToken());

        if (refreshToken.isPresent()) {
            try {
                RefreshToken token = refreshTokenService.verifyExpiration(refreshToken.get());
                String accessToken = jwtService.GenerateToken(token.getUserInfo().getUsername());
                return new ResponseEntity<>(new JwtResponseDTO(accessToken, token.getToken()), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }

        return new ResponseEntity<>("Refresh token not found in database", HttpStatus.BAD_REQUEST);
    }
}
