package pl.jointrip.services.userService.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.jointrip.dao.RoleRepository;
import pl.jointrip.dao.UserRepository;
import pl.jointrip.models.entities.user.Role;
import pl.jointrip.models.entities.user.User;
import pl.jointrip.services.userService.UserService;

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
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleRepository.findByRole("USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);

    }
    public User getLoggedUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;

        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        User user = userRepository.findByEmail(username);
        return user;
    }

    public List<User> allUsersByStatus(int status){
        return userRepository.findAllByActive(status);
    }

    public boolean changeUserStatus(int id, int status){
        User user = userRepository.findByUserId(id);
        user.setActive(status);
        try{
            userRepository.save(user);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    public boolean removeUser(int id){
        User user = userRepository.findByUserId(id);
        try{
            userRepository.deleteUserRole(user.getUserId());
            userRepository.delete(user);
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

}