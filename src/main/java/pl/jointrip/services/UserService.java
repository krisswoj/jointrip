package pl.jointrip.services;

import org.springframework.stereotype.Service;
import pl.jointrip.models.User;

public interface UserService {
    public User findUserByEmail(String email);
    public void saveUser(User user);
}
