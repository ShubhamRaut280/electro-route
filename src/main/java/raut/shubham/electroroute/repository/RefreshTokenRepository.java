package raut.shubham.electroroute.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import raut.shubham.electroroute.entity.RefreshToken;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository  extends JpaRepository<RefreshToken, Integer> {
    Optional<RefreshToken> findByToken(String token);

}
