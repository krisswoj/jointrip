package pl.jointrip.services.userService;

import org.springframework.stereotype.Service;
import pl.jointrip.models.entities.user.User;

import java.util.List;

@Service
public interface UserService {

    User findUserByEmail(String email);

    void saveUser(User user);

    void editUser(User user);

    boolean changeUserPassword(String oldPassword, String newPassword, String newPasswordVerify);

    User getLoggedUser();

    List<User> allUsersByStatus(int status);

    List<User> allNotVerifiedUsers();

    boolean changeUserStatus(int id, int status);

    boolean removeUser(int id);

    User findUserById(int id);

    boolean changeUserRole(int id, String role);
}
