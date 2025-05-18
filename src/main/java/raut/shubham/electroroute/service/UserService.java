package raut.shubham.electroroute.service;

import raut.shubham.electroroute.entity.UserInfo;

import java.util.Optional;

public interface UserService {
    Optional<UserInfo> getUserById(Long id);
    Optional<UserInfo> getSelf();
    UserInfo updateUser(UserInfo user, Long id);
    Boolean deleteUser(Long id);

}
