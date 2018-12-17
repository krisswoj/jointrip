package pl.jointrip.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jointrip.models.entities.user.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    List<User> findAllByActive(int status);
    User findByUserId(int id);
}