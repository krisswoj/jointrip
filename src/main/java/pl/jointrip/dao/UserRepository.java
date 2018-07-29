package pl.jointrip.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.jointrip.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}