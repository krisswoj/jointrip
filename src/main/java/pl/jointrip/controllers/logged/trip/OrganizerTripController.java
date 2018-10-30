package pl.jointrip.controllers.logged.trip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.jointrip.dao.TripMemberRepository;
import pl.jointrip.dao.TripRepository;
import pl.jointrip.models.CommentsWrapper;
import pl.jointrip.models.Trip;
import pl.jointrip.models.TripWrapper;
import pl.jointrip.models.TripsMemberWrapper;
import pl.jointrip.services.tripService.TripService;

import javax.validation.Valid;
import java.util.List;

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
        modelAndView.addObject("managmentTrips", tripService.tripWithStatistics());
        modelAndView.setViewName("trip/managment-trips");
        return modelAndView;
    }

    @GetMapping(value = "/myTripManagment{ids}")
    public ModelAndView getCourseDetails(@RequestParam("ids") int ids) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("tripInfo", tripRepository.findById(ids));
        modelAndView.addObject("form", tripService.tripsMemberWrapper(ids));
        modelAndView.addObject("commentsForm", tripService.commentsWrapper(ids));
        modelAndView.setViewName("trip/show-managment-trip");
        return modelAndView;
    }

    @PostMapping(value = "/myTripManagment{ids}")
    public ModelAndView changeTripMemberStatus(@ModelAttribute TripsMemberWrapper form, BindingResult result, @RequestParam("ids") int ids) {
        ModelAndView modelAndView = new ModelAndView();
        tripService.tripMemberListUpdate(form.getTripMemberList());
        modelAndView.addObject("tripInfo", tripRepository.findById(ids));
        modelAndView.addObject("form", tripService.tripsMemberWrapper(ids));
        modelAndView.addObject("commentsForm", tripService.commentsWrapper(ids));
        modelAndView.setViewName("trip/show-managment-trip");
        return modelAndView;
    }

    @PostMapping(value = "/myTripManagment/updatedComment{ids}")
    public ModelAndView CommentAnswerController(@ModelAttribute CommentsWrapper commentsForm, BindingResult result, @RequestParam("ids") int ids) {
        ModelAndView modelAndView = new ModelAndView();
        tripService.commentsListUpdateByOwner(commentsForm.getCommentsList());
        modelAndView.addObject("tripInfo", tripRepository.findById(ids));
        modelAndView.addObject("form", tripService.tripsMemberWrapper(ids));
        modelAndView.addObject("commentsForm", tripService.commentsWrapper(ids));
        modelAndView.setViewName("trip/show-managment-trip");
        return modelAndView;
    }
}
