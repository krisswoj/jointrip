package pl.jointrip.controllers.logged.trip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.jointrip.dao.TripRepository;
import pl.jointrip.models.Trip;

import javax.validation.Valid;
import java.util.Date;

@Controller
public class TripController {

    @Autowired
    TripRepository tripRepository;


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
        tripEntity.setTripCreateDate(new Date());
        tripEntity.setTripEditDate(new Date());
        tripRepository.save(tripEntity);
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
