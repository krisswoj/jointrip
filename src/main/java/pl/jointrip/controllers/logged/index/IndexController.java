package pl.jointrip.controllers.logged.index;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.jointrip.services.indexStats.IndexStatisticsService;
import pl.jointrip.services.tripService.TripService;

@Controller
public class IndexController {

    private TripService tripService;
    private IndexStatisticsService indexStatisticsService;

    @Autowired
    public IndexController(TripService tripService, IndexStatisticsService indexStatisticsService) {
        this.tripService = tripService;
        this.indexStatisticsService = indexStatisticsService;
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("indexTrips", tripService.findLatestTripsWrapper());
        modelAndView.addObject("statistics", indexStatisticsService.fetchIndexStatisticsViewModel());
        modelAndView.setViewName("index");
        return modelAndView;
    }

}
