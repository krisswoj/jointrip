package pl.jointrip.controllers.logged.trip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.jointrip.dao.TripRepository;
import pl.jointrip.services.tripService.DailyTripPlanService;
import pl.jointrip.services.userService.UserService;

@Controller
public class TravelersPanel {

    @Autowired
    TripRepository tripRepository;

    @Autowired
    UserService userService;

    @Autowired
    DailyTripPlanService dailyTripPlanService;

    @GetMapping(value = "/travelerPanel{tripId}")
    public ModelAndView travelerUserPanel(@RequestParam("tripId") int tripId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("tripContent", dailyTripPlanService.tripWithDailyPlan(dailyTripPlanService.findTripById(tripId)));
        modelAndView.setViewName("trip/traveler-panel");
        return modelAndView;
    }

}
