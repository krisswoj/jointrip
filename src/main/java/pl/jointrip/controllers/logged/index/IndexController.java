package pl.jointrip.controllers.logged.index;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.jointrip.services.indexStats.IndexStatisticsService;
import pl.jointrip.services.tripService.TripService;

@Controller
public class IndexController{

    @Autowired
    TripService tripService;
    @Autowired
    IndexStatisticsService indexStatisticsService;

    @RequestMapping(value={"/","/index"}, method = RequestMethod.GET)
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("index_form", tripService.findLatestTrips());
        modelAndView.addObject("statistics", indexStatisticsService.fetchIndexStatisticsViewModel());
        modelAndView.setViewName("index");
        return modelAndView;
    }

}
