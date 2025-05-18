package raut.shubham.electroroute.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import raut.shubham.electroroute.entity.UserInfo;
import raut.shubham.electroroute.repository.UserRepository;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;


    @Override
    public Optional<UserInfo> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<UserInfo> getSelf() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth.isAuthenticated()){
            String name = auth.getName();
            return Optional.ofNullable(userRepository.findByUsername(name));
        }
        return Optional.empty();
    }

    @Override
    public UserInfo updateUser(UserInfo user, Long id) {
        Optional<UserInfo> exUser = userRepository.findById(id);
        if(exUser.isPresent()){
            UserInfo newUser = exUser.get();
            newUser.update(user);
            userRepository.save(newUser);
            return newUser;
        }else return null;
    }

    @Override
    public Boolean deleteUser(Long id) {
        try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
