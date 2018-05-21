package pl.jointrip.services;

import pl.jointrip.models.User;

public interface UserService {
    public User findUserByEmail(String email);
    public void saveUser(User user);
}
