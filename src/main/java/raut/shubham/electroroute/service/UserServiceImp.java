package raut.shubham.electroroute.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import raut.shubham.electroroute.entity.User;
import raut.shubham.electroroute.entity.dto.SignInRequest;
import raut.shubham.electroroute.entity.dto.SignupRequest;
import raut.shubham.electroroute.repository.UserRepository;

import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String saveUser(User user) {
        return userRepository.save(user).getUserId();
    }

    @Override
    public String registerUser(SignupRequest signupRequest) {
        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setFirstName(signupRequest.getFirstName());
        user.setLastName(signupRequest.getLastName());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        userRepository.save(user);
        return user.getUserId();
    }

    @Override
    public Boolean verifyUser(User user, SignInRequest signInRequest) {
        return passwordEncoder.matches(signInRequest.getPassword(),user.getPassword());
    }

    @Override
    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }
}