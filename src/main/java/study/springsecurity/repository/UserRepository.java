package study.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.springsecurity.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
