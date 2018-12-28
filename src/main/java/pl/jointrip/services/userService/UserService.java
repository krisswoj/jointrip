package pl.jointrip.services.userService;

import org.springframework.stereotype.Service;
import pl.jointrip.models.entities.user.User;

import java.util.List;

@Service
public interface UserService {

    User findUserByEmail(String email);

    void saveUser(User user);

    User getLoggedUser();

    List<User> allUsersByStatus(int status);

    boolean changeUserStatus(int id, int status);

    boolean removeUser(int id);
}
