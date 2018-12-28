package pl.jointrip.controllers.logged.trip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.jointrip.models.entities.trip.Trip;
import pl.jointrip.models.system.SystemNotification;
import pl.jointrip.services.tripService.TripService;

import javax.validation.Valid;

@Controller
public class AddTripController {

    @Autowired
    TripService tripService;

    @Value("${TRIP_ADDED_POSITIVE}")
    private String tripPositive;

    @Value("${TRIP_ADDED_NEGATIVE}")
    private String tripNegative;


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
        boolean result = tripService.addedTripNotification(tripEntity);
        SystemNotification systemNotification = result ? new SystemNotification("true", tripPositive) : new SystemNotification("fail", tripNegative);
        modelAndView.addObject("message", systemNotification);
        modelAndView.setViewName("trip/add-trip-form");
        return modelAndView;
    }
}
