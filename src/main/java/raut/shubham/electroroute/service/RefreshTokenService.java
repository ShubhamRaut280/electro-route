package raut.shubham.electroroute.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raut.shubham.electroroute.entity.RefreshToken;
import raut.shubham.electroroute.entity.UserInfo;
import raut.shubham.electroroute.repository.RefreshTokenRepository;
import raut.shubham.electroroute.repository.UserRepository;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {

    @Autowired
    RefreshTokenRepository refreshTokenRepository;
    @Autowired
    UserRepository userRepository;


    public RefreshToken createRefreshToken(String username){
        UserInfo extractedUser = userRepository.findByUsername(username);

        RefreshToken refreshToken = RefreshToken.builder()
                .userInfo(extractedUser)
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusMillis(600000))
                .build();

        return refreshTokenRepository.save(refreshToken);
    }

    public RefreshToken verifyExpiration(RefreshToken token){
        if(token.getExpiryDate().compareTo(Instant.now()) < 0){
            refreshTokenRepository.delete(token);
            throw new RuntimeException("Refresh token is expired, Please make a new login!");
        }

        return token;
    }

    public Optional<RefreshToken> findByToken (String token){
        return refreshTokenRepository.findByToken(token);
    }
}
