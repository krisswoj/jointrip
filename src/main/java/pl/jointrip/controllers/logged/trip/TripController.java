package pl.jointrip.controllers.logged.trip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.jointrip.models.entities.comments.Comments;
import pl.jointrip.services.tripService.TripService;
import pl.jointrip.services.userService.UserService;

@Controller
public class TripController {

    @Autowired
    TripService tripService;
    @Autowired
    UserService userService;


    @GetMapping(value = "/showTrips")
    public ModelAndView showTrips() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("showTripsWrapper", tripService.tripsWithStatisicForNoMemberUsers());
        modelAndView.setViewName("trip/trips");
        return modelAndView;
    }

    @GetMapping(value = "/trips")
    public ModelAndView showTripsForUnlogged() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("showTripsWrapper", tripService.findAllActiveTripsForNoLogUser());
        modelAndView.setViewName("trip/trips");
        return modelAndView;
    }

    @GetMapping(value = "/showTrip", params = "ide")
    public ModelAndView showTrip(@RequestParam("ide") int tripId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userInfo", userService.getLoggedUser());
        modelAndView.addObject("tripInfo", tripService.findById(tripId));
        modelAndView.addObject("userIsAMember", tripService.existsTripByTripMembers(tripService.findById(tripId), userService.getLoggedUser()));
        modelAndView.addObject("commentForm", new Comments());
        modelAndView.addObject("commentList", tripService.findByTripAndStatusIs(tripService.findById(tripId), 1));
        modelAndView.setViewName("trip/show-trip");
        return modelAndView;
    }

    @PostMapping(value = "/showTrip/addedComment", params = "ide")
    public ModelAndView addCommentForm(@ModelAttribute("comment") Comments commentForm, @RequestParam("ide") int tripId) {
        ModelAndView modelAndView = new ModelAndView();
        tripService.addedCommentNotification(commentForm, tripId);
        modelAndView.addObject("tripInfo", tripService.findById(tripId));
        modelAndView.addObject("commentForm", new Comments());
        modelAndView.addObject("commentList", tripService.findByTripAndStatusIs(tripService.findById(tripId), 1));

        modelAndView.setViewName("trip/show-trip");
        return modelAndView;
    }

    @GetMapping(value = "/showTrip", params = "join")
    public ModelAndView joinToTrip(@RequestParam("join") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("tripInfo", tripService.findById(id));
        modelAndView.addObject("commentForm", new Comments());
        modelAndView.addObject("commentList", tripService.findByTripAndStatusIs(tripService.findById(id), 1));
        modelAndView.addObject("message", tripService.joinedTripNotification(id));
        modelAndView.addObject("userIsAMember", tripService.existsTripByTripMembers(tripService.findById(id), userService.getLoggedUser()));
        modelAndView.setViewName("trip/show-trip");
        return modelAndView;
    }
}