package pl.jointrip.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.jointrip.models.entities.user.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    List<User> findAllByActive(int status);
    User findByUserId(int id);
    long countByActive(int active);
    @Modifying
    @Transactional
    @Query("delete from UserRole u where u.userId = :user_id")
    void deleteUserRole(@Param("user_id") int user_id);
}