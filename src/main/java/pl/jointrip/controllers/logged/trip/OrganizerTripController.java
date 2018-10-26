package pl.jointrip.controllers.logged.trip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.jointrip.dao.TripMemberRepository;
import pl.jointrip.dao.TripRepository;
import pl.jointrip.models.Trip;
import pl.jointrip.models.TripMember;
import pl.jointrip.services.tripService.TripService;

import javax.validation.Valid;

@Controller
public class OrganizerTripController {

    @Autowired
    TripService tripService;
    @Autowired
    TripRepository tripRepository;
    @Autowired
    TripMemberRepository tripMemberRepository;

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

    @GetMapping(value = "/myTripsManagment")
    public ModelAndView tripsManagmentList() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("managmentTrips", tripService.findTripByUserByUserId());
        modelAndView.setViewName("trip/managment-trips");
        return modelAndView;
    }

    @GetMapping(value = "/myTripManagment{ids}")
    public ModelAndView getCourseDetails(@RequestParam("ids") int ids) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("tripInfo", tripRepository.findById(ids));
        modelAndView.setViewName("trip/show-managment-trip");
        return modelAndView;
    }

    @PostMapping(value = "/myTripManagment{ids}")
    public ModelAndView changePaymentStatus(@ModelAttribute("tripInfo2") TripMember tripMember,
                                            BindingResult result,
                                            @RequestParam("ids") int ids) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("tripInfo", tripRepository.findById(ids));
        modelAndView.addObject("tripInfo2", tripMemberRepository.save(tripMember));
        modelAndView.setViewName("trip/show-managment-trip");
        return modelAndView;
    }

}
