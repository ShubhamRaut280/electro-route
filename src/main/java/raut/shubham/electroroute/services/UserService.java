package raut.shubham.electroroute.services;

import raut.shubham.electroroute.models.User;
import raut.shubham.electroroute.payload.SignInRequest;
import raut.shubham.electroroute.payload.SignupRequest;

import java.util.Optional;

public interface UserService {

    String saveUser(User user);

    String registerUser(SignupRequest signupRequest);

    Boolean verifyUser(User user, SignInRequest signInRequest);

    Optional<User> findById(String id);
}
