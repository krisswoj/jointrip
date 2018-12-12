package pl.jointrip.controllers.logged.trip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.jointrip.models.entities.trip.Trip;
import pl.jointrip.services.tripService.TripService;

import javax.validation.Valid;

@Controller
public class AddTripController {

    @Autowired
    TripService tripService;


    @GetMapping(value = "/addTrip")
    public ModelAndView addTripForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("trip_form", new Trip());
        modelAndView.setViewName("trip/add-trip-form");
        return modelAndView;
    }

    @PostMapping(value = "/addTrip")
    public ModelAndView addTripForm(@Valid Trip tripEntity) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("trip_form", new Trip());
        modelAndView.addObject("message", tripService.addedTripNotification(tripEntity));
        modelAndView.setViewName("trip/add-trip-form");
        return modelAndView;
    }
}
