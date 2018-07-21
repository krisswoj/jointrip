package pl.jointrip.controllers.logged.index;

import org.springframework.boot.web.servlet.error.ErrorController;
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
public class IndexController implements ErrorController{

    @RequestMapping(value={"/","/index"}, method = RequestMethod.GET)
    @ModelAttribute("user")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("index");
        return modelAndView;
    }


    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public String error() {
        return "/error/error";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }


}
