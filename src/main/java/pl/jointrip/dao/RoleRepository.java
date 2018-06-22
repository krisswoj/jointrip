package pl.jointrip.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.jointrip.models.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{
    Role findByRole(String role);

}
