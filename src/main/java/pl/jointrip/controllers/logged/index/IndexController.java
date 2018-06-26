package pl.jointrip.controllers.logged.index;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.jointrip.models.User;
import pl.jointrip.services.UserService;

import javax.validation.Valid;

@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @RequestMapping(value={"/","/index"}, method = RequestMethod.GET)
    @ModelAttribute("user")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    @ModelAttribute("user")
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("index");
            return modelAndView;
        } else {
            ModelAndView mv = new ModelAndView("redirect:/home/admin");
            userService.saveUser(user);
            mv.addObject("user", new User());
            return mv;

        }
    }

}
