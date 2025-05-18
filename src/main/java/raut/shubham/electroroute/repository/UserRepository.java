package raut.shubham.electroroute.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import raut.shubham.electroroute.entity.User;

@Repository
public interface UserRepository extends MongoRepository<User,String> {

    Boolean existsByEmail(String email);

    User findByEmail(String email);
}