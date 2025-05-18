package raut.shubham.electroroute.service;

import raut.shubham.electroroute.entity.User;
import raut.shubham.electroroute.entity.dto.SignInRequest;
import raut.shubham.electroroute.entity.dto.SignupRequest;

import java.util.Optional;

public interface UserService {

    String saveUser(User user);

    String registerUser(SignupRequest signupRequest);

    Boolean verifyUser(User user, SignInRequest signInRequest);

    Optional<User> findById(String id);
}
