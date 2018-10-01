package pl.jointrip.services.userService;

import org.springframework.stereotype.Service;
import pl.jointrip.models.User;

public interface UserService {
    public User findUserByEmail(String email);
    public void saveUser(User user);
    User getLoggedUser();
}
