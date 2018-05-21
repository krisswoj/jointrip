package pl.jointrip.services;

import pl.jointrip.domain.User;

public interface UserService {
    public User findUserByEmail(String email);
    public void saveUser(User user);
}
