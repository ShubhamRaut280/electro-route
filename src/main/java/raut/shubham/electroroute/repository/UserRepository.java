package raut.shubham.electroroute.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import raut.shubham.electroroute.entity.UserInfo;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, Long> {
     UserInfo findByUsername(String username);
}
