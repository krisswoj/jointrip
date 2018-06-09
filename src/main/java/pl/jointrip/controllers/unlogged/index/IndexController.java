package pl.jointrip.controllers.unlogged.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @RequestMapping(value={"/","/nonUser/index"}, method = RequestMethod.GET)
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("nonUser/index");
        return modelAndView;
    }

}
