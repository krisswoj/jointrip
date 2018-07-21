package pl.jointrip.controllers.logged.index;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class IndexController implements ErrorController{

    @RequestMapping(value={"/","/index"}, method = RequestMethod.GET)
    public ModelAndView index(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        ModelAndView modelAndView = new ModelAndView();
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
