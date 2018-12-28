package pl.jointrip.controllers.logged.trip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.jointrip.services.tripService.TripService;
import pl.jointrip.services.userService.UserService;

@Controller
public class MyTripsController {
    @Autowired
    TripService tripService;
    @Autowired
    UserService userService;

    @GetMapping(value = "/myTrips")
    public ModelAndView myTrips() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userInfo", userService.getLoggedUser());
        modelAndView.addObject("showTripsWrapper", tripService.allLoggedUserTrips());
        modelAndView.addObject("showTripsAmountInfo", tripService.amountOfTripsForUser());
        modelAndView.setViewName("trip/my-trips");
        return modelAndView;
    }
}
