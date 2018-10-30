package pl.jointrip.controllers.logged.trip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.jointrip.dao.CommentsRepository;
import pl.jointrip.dao.TripRepository;
import pl.jointrip.dao.UserRepository;
import pl.jointrip.models.Comments;
import pl.jointrip.services.tripService.TripService;
import pl.jointrip.services.userService.UserService;

@Controller
public class TripController {

    @Autowired
    TripRepository tripRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CommentsRepository commentsRepository;
    @Autowired
    TripService tripService;
    @Autowired
    UserService userService;


    @GetMapping(value = "/showTrips")
    public ModelAndView showTrips() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userInfo", userService.getLoggedUser());
        modelAndView.addObject("show_trips", tripService.findTripByTripMembersNot());
        modelAndView.setViewName("trip/trips");
        return modelAndView;
    }

    @GetMapping(value = "/showTrip", params = "ide")
    public ModelAndView showTrip(@RequestParam("ide") int tripId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userInfo", userService.getLoggedUser());
        modelAndView.addObject("tripInfo", tripRepository.findById(tripId));
        modelAndView.addObject("commentForm", new Comments());
        modelAndView.addObject("commentList", commentsRepository.findByTripAndStatusIs(tripRepository.findById(tripId), 1));
        modelAndView.setViewName("trip/show-trip");
        return modelAndView;
    }

    @PostMapping(value = "/showTrip/addedComment", params = "ide")
    public ModelAndView addCommentForm(@ModelAttribute Comments commentForm, @RequestParam("ide") int tripId) {
        ModelAndView modelAndView = new ModelAndView();
        tripService.addedCommentNotification(commentForm, tripId);
        modelAndView.addObject("tripInfo", tripRepository.findById(tripId));
        modelAndView.addObject("commentForm", new Comments());
        modelAndView.addObject("commentList", commentsRepository.findByTripAndStatusIs(tripRepository.findById(tripId), 1));

        modelAndView.setViewName("trip/show-trip");
        return modelAndView;
    }

    @GetMapping(value = "/showTrip", params = "join")
    public ModelAndView joinToTrip(@RequestParam("join") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("tripInfo", tripRepository.findById(id));
        modelAndView.addObject("commentForm", new Comments());
        modelAndView.addObject("commentList", commentsRepository.findByTripAndStatusIs(tripRepository.findById(id), 1));
        modelAndView.addObject("message", tripService.joinedTripNotification(id));
        modelAndView.setViewName("trip/show-trip");
        return modelAndView;
    }

    @GetMapping(value = "/myTrips")
    public ModelAndView myTrips() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userInfo", userService.getLoggedUser());
        modelAndView.addObject("show_trips", tripService.joinedTripsByUser());
        modelAndView.setViewName("trip/trips");
        return modelAndView;
    }
}