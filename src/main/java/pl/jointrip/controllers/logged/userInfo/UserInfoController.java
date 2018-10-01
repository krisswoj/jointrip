package pl.jointrip.controllers.logged.userInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.jointrip.models.Trip;
import pl.jointrip.models.User;
import pl.jointrip.services.userService.UserService;

import javax.annotation.PostConstruct;

@Controller
public class UserInfoController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/user/edit")
    public ModelAndView showTrips() {
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.getLoggedUser();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("user/edit");
        return modelAndView;
    }
}
