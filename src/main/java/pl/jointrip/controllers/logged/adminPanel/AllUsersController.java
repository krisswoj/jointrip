package pl.jointrip.controllers.logged.adminPanel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.jointrip.services.userService.UserService;

@Controller
public class AllUsersController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "admin/allUsersPanel")
    public ModelAndView fetchUsers(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("users", userService.allUsersByStatus(1));
        modelAndView.setViewName("admin/allUsersPanel");
        return modelAndView;
    }

    @GetMapping(value = "admin/allUsersPanel/block", params = "id")
    public ModelAndView blockTrip(@RequestParam("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        userService.changeUserStatus(id, 2);
        modelAndView.addObject("users", userService.allUsersByStatus(1));
        modelAndView.setViewName("admin/allUsersPanel");
        return modelAndView;
    }
    @GetMapping(value = "admin/allUsersPanel/remove", params = "id")
    public ModelAndView removeTrip(@RequestParam("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        boolean result = userService.removeUser(id);
        modelAndView.addObject("users", userService.allUsersByStatus(1));
        modelAndView.setViewName("admin/allUsersPanel");
        return modelAndView;
    }
}
