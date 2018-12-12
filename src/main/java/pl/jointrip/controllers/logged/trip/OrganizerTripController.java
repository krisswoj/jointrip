package pl.jointrip.controllers.logged.trip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.jointrip.dao.TripMemberRepository;
import pl.jointrip.dao.TripRepository;
import pl.jointrip.models.entities.comments.CommentsWrapper;
import pl.jointrip.models.entities.trip.TripsMemberWrapper;
import pl.jointrip.services.tripService.TripService;

@Controller
public class OrganizerTripController {

    @Autowired
    TripService tripService;
    @Autowired
    TripRepository tripRepository;
    @Autowired
    TripMemberRepository tripMemberRepository;

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
