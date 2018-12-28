package pl.jointrip.controllers.logged.adminPanel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.jointrip.models.system.SystemNotification;
import pl.jointrip.services.userService.UserService;

@Controller
public class AllUsersController {

    @Autowired
    private UserService userService;

    @Value("${USER_BLOCK_POSITIVE}")
    private String userBlockPositive;

    @Value("${USER_BLOCK_NEGATIVE}")
    private String userBlockNegative;

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
        boolean result = userService.changeUserStatus(id, 2);
        SystemNotification systemNotification = result ? new SystemNotification("true", userBlockPositive) : new SystemNotification("fail", userBlockNegative);
        modelAndView.addObject("message", systemNotification);
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
