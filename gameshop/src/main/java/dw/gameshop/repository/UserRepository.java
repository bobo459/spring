package dw.gameshop.repository;

import dw.gameshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUserId(String userId);
    Optional<User> findByUserName(String userName);  //만들어야 findByUserName을 사용할수있다.
}
