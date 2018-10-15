package pl.jointrip.controllers.logged.trip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.jointrip.dao.TripRepository;
import pl.jointrip.dao.UserRepository;
import pl.jointrip.models.Comments;
import pl.jointrip.models.Trip;
import pl.jointrip.services.tripService.TripService;
import pl.jointrip.services.userService.UserService;

import javax.validation.Valid;

@Controller
public class TripController {

    @Autowired
    TripRepository tripRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TripService tripService;
    @Autowired
    UserService userService;


    @RequestMapping(value = "/add-trip", method = RequestMethod.GET)
    public ModelAndView addTripForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("trip_form", new Trip());
        modelAndView.setViewName("trip/add-trip-form");
        return modelAndView;
    }

    @RequestMapping(value = "/add-trip", method = RequestMethod.POST)
    public ModelAndView addTripForm(@Valid Trip tripEntity) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("trip_form", new Trip());

        if(tripService.saveTrip(tripEntity)){
            modelAndView.addObject("message", "Wycieczkę dodano pomyślnie!");
        }
        else{
            modelAndView.addObject("message", "Nie udało się dodać wycieczki");
        }
        modelAndView.setViewName("trip/add-trip-form");
        return modelAndView;

    }

    @RequestMapping(value = "/show-trip", params = "ide", method = RequestMethod.GET)
    public ModelAndView showTrip(@RequestParam("ide") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userInfo", userService.getLoggedUser());
        modelAndView.addObject("tripInfo", tripRepository.findById(id));
        modelAndView.addObject("commentForm", new Comments());
        modelAndView.setViewName("trip/show-trip");
        return modelAndView;
    }

    @RequestMapping(value = "/show-trip", params = "ide", method = RequestMethod.POST)
    public ModelAndView addCommentForm(@Valid Comments commentEntity, @RequestParam("ide") int tripId) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("tripInfo", tripRepository.findById(tripId));
        modelAndView.addObject("commentForm", new Comments());

        if(tripService.saveComment(commentEntity, tripId)){
            modelAndView.addObject("message", "Wycieczkę dodano pomyślnie!");
        }
        else{
            modelAndView.addObject("message", "Nie udało się dodać wycieczki");
        }
        modelAndView.setViewName("trip/show-trip");
        return modelAndView;
    }

    @RequestMapping(value = "/show-trip", params = "join", method = RequestMethod.GET)
    public ModelAndView joinToTrip(@RequestParam("join") int id){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("commentForm", new Comments());
        modelAndView.addObject("tripInfo", tripRepository.findById(id));

        if(tripService.joinToTripByUser(id)){
            modelAndView.addObject("message", "Dołączyłeś do wycieczki");
        }
        else{
            modelAndView.addObject("message", "Nie możesz dołączyć do własnej wycieczki");
        }

        modelAndView.setViewName("trip/show-trip");
        return modelAndView;
    }

    @RequestMapping(value = "/show-trips", method = RequestMethod.GET)
    public ModelAndView showTrips() {
        ModelAndView modelAndView = new ModelAndView();
        Iterable<Trip> trips = tripRepository.findAll();
        modelAndView.addObject("userInfo", userService.getLoggedUser());
        modelAndView.addObject("show_trips", trips);
        modelAndView.setViewName("trip/trips");
        return modelAndView;
    }

    @RequestMapping(value = "/my-trips", method = RequestMethod.GET)
    public ModelAndView myTrips() {
        ModelAndView modelAndView = new ModelAndView();
        Iterable<Trip> trips = tripService.joinedTripByUser();
        modelAndView.addObject("userInfo", userService.getLoggedUser());
        modelAndView.addObject("show_trips", trips);
        modelAndView.setViewName("trip/trips");
        return modelAndView;
    }

}
