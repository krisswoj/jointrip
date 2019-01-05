package pl.jointrip.controllers.logged.trip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.jointrip.models.viewModels.tripSearch.TripSearchVM;
import pl.jointrip.services.tripService.TripService;
import pl.jointrip.services.userService.UserService;

import javax.validation.Valid;

@Controller
public class MyTripsController {

    private TripService tripService;
    private UserService userService;

    @Autowired
    public MyTripsController(TripService tripService, UserService userService) {
        this.tripService = tripService;
        this.userService = userService;
    }

    @GetMapping(value = "/myTrips")
    public ModelAndView myTrips() {
        ModelAndView modelAndView = mavMyTrips();
        modelAndView.setViewName("trip/my-trips");
        return modelAndView;
    }

    @PostMapping(value = "/myTrips")
    public ModelAndView showTrips(@Valid TripSearchVM trip) {
        ModelAndView modelAndView = mavMyTrips();
        modelAndView.addObject("showTripsWrapper", tripService.searchTrips(trip, true));
        modelAndView.setViewName("trip/trips");
        return modelAndView;
    }

    private ModelAndView mavMyTrips(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userInfo", userService.getLoggedUser());
        modelAndView.addObject("showTripsWrapper", tripService.allLoggedUserTrips());
        modelAndView.addObject("showTripsAmountInfo", tripService.amountOfTripsForUser());
        modelAndView.addObject("tripSearch", new TripSearchVM());
        return modelAndView;
    }
}
