package pl.jointrip.controllers.logged.trip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.jointrip.dao.TripRepository;
import pl.jointrip.dao.UserRepository;
import pl.jointrip.models.User;
import pl.jointrip.models.Trip;
import pl.jointrip.services.TripService;

import javax.validation.Valid;
import java.util.Date;

@Controller
public class TripController {

    @Autowired
    TripRepository tripRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TripService tripService;


    @RequestMapping(value = "/add_trip", method = RequestMethod.GET)
    public ModelAndView addTripForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("trip_form", new Trip());
        modelAndView.setViewName("trip/add_trip_form");
        return modelAndView;
    }

    @RequestMapping(value = "/add_trip", method = RequestMethod.POST)
    public ModelAndView addTripForm(@Valid Trip tripEntity) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("trip_form", new Trip());

        if(tripService.saveTrip(tripEntity)){
            modelAndView.addObject("message", "Wycieczke dodano pomysleni!");
        }
        else{
            modelAndView.addObject("message", "Sorry ale cos sie wyjebalo");
        }
        modelAndView.setViewName("trip/add_trip_form");
        return modelAndView;

    }


    @RequestMapping(value = "/show_trips", method = RequestMethod.GET)
    public ModelAndView showTrips() {
        ModelAndView modelAndView = new ModelAndView();
        Iterable<Trip> trips = tripRepository.findAll();
        modelAndView.addObject("show_trips", trips);
        modelAndView.setViewName("trip/trips");
        return modelAndView;


    }
}
