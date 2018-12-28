package pl.jointrip.controllers.logged.userInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.jointrip.models.entities.user.User;
import pl.jointrip.services.userService.UserService;

import javax.validation.Valid;

@Controller
public class UserInfoController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/user/edit")
    public ModelAndView fetchUserEdit() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userEdit", new User());
        modelAndView.setViewName("user/edit");
        return modelAndView;
    }
    @PostMapping(value = "/user/edit")
    public ModelAndView editUser(@Valid User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userEdit", new User());
        User userToEdit = userService.getLoggedUser();
        userToEdit.setName(user.getName());
        userToEdit.setLastName(user.getLastName());
        //userRepository.save(userToEdit);
        modelAndView.setViewName("user/edit");
        return modelAndView;
    }
    @GetMapping(value = "/user/info")
    public ModelAndView fetchUserInfo() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userInfo", userService.getLoggedUser());
        modelAndView.setViewName("user/info");
        return modelAndView;
    }

}
