package raut.shubham.electroroute.controller;

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


@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController
{

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private JwtService jwtService;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("/signup")
    public ResponseEntity SignUp(@RequestBody UserInfoDto userInfoDto){

        if (userInfoDto.getUsername() == null || userInfoDto.getUsername().isEmpty()) {
            return new ResponseEntity<>("Email cannot be empty", HttpStatus.BAD_REQUEST);
        }else if(!isValidEmail(userInfoDto.getUsername())){
            return new ResponseEntity<>("Invalid email", HttpStatus.BAD_REQUEST);
        }

        if (userInfoDto.getPassword() == null || userInfoDto.getPassword().isEmpty()) {
            return new ResponseEntity<>("Password cannot be empty", HttpStatus.BAD_REQUEST);
        }else if (!isValidPassword(userInfoDto.getPassword())){
            return new ResponseEntity<>("Password should have at least 8 characters, one digit, one lowercase, one uppercase, one special character", HttpStatus.BAD_REQUEST);
        }


        try{
            Boolean isSignUped = userDetailsService.signupUser(userInfoDto);
            if(Boolean.FALSE.equals(isSignUped)){
                return new ResponseEntity<>("Already Exist", HttpStatus.BAD_REQUEST);
            }
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(userInfoDto.getUsername());
            String jwtToken = jwtService.GenerateToken(userInfoDto.getUsername());
            return new ResponseEntity<>(new JwtResponseDTO(jwtToken, refreshToken.getToken()), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>("Exception in User Service", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/login")
    public ResponseEntity AuthenticateAndGetToken(@RequestBody AuthRequestDTO authRequestDTO){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));
        if(authentication.isAuthenticated()){
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(authRequestDTO.getUsername());
            return new ResponseEntity<>(JwtResponseDTO.builder()
                    .accessToken(jwtService.GenerateToken(authRequestDTO.getUsername()))
                    .token(refreshToken.getToken())
                    .build(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Exception in User Service", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/refreshToken")
    public ResponseEntity refreshToken(@RequestBody RefreshTokenRequestDTO refreshTokenRequestDTO){

        Optional<RefreshToken> refreshToken = refreshTokenService.findByToken(refreshTokenRequestDTO.getToken());

        if(refreshToken.isPresent()){
            try{
                RefreshToken token = refreshTokenService.verifyExpiration(refreshToken.get());
                String accessToken = jwtService.GenerateToken(token.getUserInfo().getUsername());
                return new ResponseEntity(new JwtResponseDTO(accessToken,token.getToken()), HttpStatus.OK);
            }catch (Exception e){
                return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }

        return new ResponseEntity("Refresh Token is not in DB..!!", HttpStatus.BAD_REQUEST);

    }


}
