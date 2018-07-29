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
import pl.jointrip.models.TripEntity;
import pl.jointrip.models.User;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
public class TripController {

    @Autowired
    TripRepository tripRepository;
    @Autowired
    UserRepository userRepository;


    @RequestMapping(value = "/add_trip", method = RequestMethod.GET)
    public ModelAndView addTripForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("trip_form", new TripEntity());
        modelAndView.setViewName("trip/add_trip_form");
        return modelAndView;
    }

    @RequestMapping(value = "/add_trip", method = RequestMethod.POST)
    public ModelAndView addTripForm(@Valid TripEntity tripEntity) {

        UserDetails userDetails =
                (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByEmail(userDetails.getUsername());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("trip_form", new TripEntity());
        tripEntity.setTripCreateDate(new Date());
        tripEntity.setTripEditDate(new Date());
        tripEntity.setUserId(user.getId());
        tripRepository.save(tripEntity);
        modelAndView.setViewName("trip/add_trip_form");
        return modelAndView;

    }


    @RequestMapping(value = "/show_trips", method = RequestMethod.GET)
    public ModelAndView showTrips() {
        ModelAndView modelAndView = new ModelAndView();
        Iterable<TripEntity> trips = tripRepository.findAll();
        modelAndView.addObject("show_trips", trips);
        modelAndView.setViewName("trip/trips");
        return modelAndView;


    }
}
