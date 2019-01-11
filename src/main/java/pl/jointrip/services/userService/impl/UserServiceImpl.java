package pl.jointrip.services.userService.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.jointrip.dao.RoleRepository;
import pl.jointrip.dao.UserRepository;
import pl.jointrip.dao.UserRoleRepository;
import pl.jointrip.models.entities.user.Role;
import pl.jointrip.models.entities.user.User;
import pl.jointrip.models.entities.user.UserRole;
import pl.jointrip.services.userService.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findUserById(int id){
        return userRepository.findByUserId(id);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleRepository.findByRole("NOTVERIFIEDUSER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

    @Override
    public void editUser(User user) {
        userRepository.save(user);
    }

    @Override
    public boolean changeUserPassword(String oldPassword, String newPassword, String newPasswordVerify) {
        User user = this.getLoggedUser();

        if (!bCryptPasswordEncoder.matches(oldPassword, user.getPassword()))
            return false;

        if (!newPassword.equals(newPasswordVerify))
            return false;

        try {
            user.setPassword(bCryptPasswordEncoder.encode(newPassword));
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public User getLoggedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;

        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return userRepository.findByEmail(username);
    }

    public List<User> allUsersByStatus(int status) {
        return userRepository.findAllByActive(status);
    }

    public List<User> allNotVerifiedUsers(){
        List<User> users = new ArrayList<>();
        for(UserRole userRole : userRoleRepository.findAllByRoleId(3)){
            User user = userRepository.findByUserId(userRole.getUserId());
            users.add(user);
        }

        return users;
    }

    public boolean changeUserStatus(int id, int status) {
        User user = userRepository.findByUserId(id);
        user.setActive(status);
        try {
            userRepository.save(user);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean removeUser(int id) {
        User user = userRepository.findByUserId(id);
        try {
            userRepository.deleteUserRole(user.getUserId());
            userRepository.delete(user);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean changeUserRole(int id, String role){
        User user = userRepository.findByUserId(id);
        Role userRole = roleRepository.findByRole("USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        try {
            userRepository.save(user);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}