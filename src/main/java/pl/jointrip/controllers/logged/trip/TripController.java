package pl.jointrip.controllers.logged.trip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.jointrip.models.entities.comments.Comments;
import pl.jointrip.models.viewModels.tripSearch.TripSearchVM;
import pl.jointrip.services.tripService.TripService;
import pl.jointrip.services.userService.UserService;

import javax.validation.Valid;

@Controller
public class TripController {

    private TripService tripService;
    private UserService userService;

    @Autowired
    public TripController(TripService tripService, UserService userService) {
        this.tripService = tripService;
        this.userService = userService;
    }

    @GetMapping(value = "/showTrips")
    public ModelAndView showTrips() {
        ModelAndView modelAndView = mavWithTripSearch();
        modelAndView.addObject("showTripsWrapper", tripService.tripsWithStatisicForNoMemberUsers());
        modelAndView.setViewName("trip/trips");
        return modelAndView;
    }

    @PostMapping(value = "/showTrips")
    public ModelAndView showTrips(@Valid TripSearchVM trip) {
        ModelAndView modelAndView = mavWithFilledTripSearch(trip);
        modelAndView.addObject("showTripsWrapper", tripService.searchTrips(trip, true));
        modelAndView.setViewName("trip/trips");
        return modelAndView;
    }

    @GetMapping(value = "/trips")
    public ModelAndView showTripsForUnlogged() {
        ModelAndView modelAndView = mavWithTripSearch();
        modelAndView.addObject("showTripsWrapper", tripService.findAllActiveTripsForNoLogUser());
        modelAndView.setViewName("trip/trips");
        return modelAndView;
    }

    @PostMapping(value = "/trips")
    public ModelAndView searchTripsForUnlogged(@Valid TripSearchVM trip) {
        ModelAndView modelAndView = mavWithFilledTripSearch(trip);
        modelAndView.addObject("showTripsWrapper", tripService.searchTrips(trip, false));
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
        ModelAndView modelAndView = mavWithCommentAndJoin(tripId);
        modelAndView.addObject("message", tripService.addedCommentNotification(commentForm, tripId));
        modelAndView.addObject("commentList", tripService.findByTripAndStatusIs(tripService.findById(tripId), 1));
        modelAndView.setViewName("trip/show-trip");
        return modelAndView;
    }

    @GetMapping(value = "/showTrip", params = "join")
    public ModelAndView joinToTrip(@RequestParam("join") int tripId) {
        ModelAndView modelAndView = mavWithCommentAndJoin(tripId);
        modelAndView.addObject("message", tripService.joinedTripNotification(tripId));
        modelAndView.addObject("commentList", tripService.findByTripAndStatusIs(tripService.findById(tripId), 1));
        modelAndView.addObject("userIsAMember", tripService.existsTripByTripMembers(tripService.findById(tripId), userService.getLoggedUser()));
        modelAndView.setViewName("trip/show-trip");
        return modelAndView;
    }

    private ModelAndView mavWithTripSearch() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("tripSearch", new TripSearchVM());
        return modelAndView;
    }

    private ModelAndView mavWithFilledTripSearch(TripSearchVM tripSearchVM) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("tripSearch", tripSearchVM);
        return modelAndView;
    }

    private ModelAndView mavWithCommentAndJoin(int tripId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("tripInfo", tripService.findById(tripId));
        modelAndView.addObject("commentForm", new Comments());
        return modelAndView;
    }
}