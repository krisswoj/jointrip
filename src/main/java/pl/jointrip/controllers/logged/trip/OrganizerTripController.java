package pl.jointrip.controllers.logged.trip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.jointrip.models.entities.comments.CommentsWrapper;
import pl.jointrip.models.entities.trip.DailyTripPlan;
import pl.jointrip.models.entities.trip.TripsMemberWrapper;
import pl.jointrip.services.tripService.DailyTripPlanService;
import pl.jointrip.services.tripService.TripService;

@Controller
public class OrganizerTripController {

    @Autowired
    TripService tripService;

    @Autowired
    DailyTripPlanService dailyTripPlanService;

    @GetMapping(value = "/myTripsManagment")
    public ModelAndView tripsManagmentList() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("managmentTrips", tripService.tripWithStatisticsForOrganisator());
        modelAndView.setViewName("trip/managment-trips");
        return modelAndView;
    }

    @GetMapping(value = "/myTripManagment{ids}")
    public ModelAndView getCourseDetails(@RequestParam("ids") int ids) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("tripInfo", dailyTripPlanService.tripWithDailyPlan(tripService.findById(ids)));
        modelAndView.addObject("form", tripService.tripsMemberWrapper(ids));
        modelAndView.addObject("commentsForm", tripService.commentsWrapper(ids));
        modelAndView.setViewName("trip/show-managment-trip-main");
        return modelAndView;
    }

    @GetMapping(value = "/myTripManagment/users{ids}")
    public ModelAndView getCourseDetailsUsers(@RequestParam("ids") int ids) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("tripInfo", dailyTripPlanService.tripWithDailyPlan(tripService.findById(ids)));
        modelAndView.addObject("form", tripService.tripsMemberWrapper(ids));
        modelAndView.addObject("commentsForm", tripService.commentsWrapper(ids));
        modelAndView.setViewName("trip/show-managment-trip-users");
        return modelAndView;
    }


    @GetMapping(value = "/myTripManagment/travelerPanel{ids}")
    public ModelAndView travelerPanelOrganisator(@RequestParam("ids") int ids) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("tripInfo", dailyTripPlanService.tripWithDailyPlan(tripService.findById(ids)));
        modelAndView.addObject("dailyPlanForm", new DailyTripPlan());
        modelAndView.setViewName("trip/show-managment-trip-plan-trip");
        return modelAndView;
    }

    @PostMapping(value = "/myTripManagment/travelerPanel{ids}")
    public ModelAndView travelerPanelOrganisatorAddNewPlan(@ModelAttribute DailyTripPlan dailyTripPlan, @RequestParam("ids") int ids) {
        ModelAndView modelAndView = new ModelAndView();
        dailyTripPlanService.addNewDailyPlan(dailyTripPlan, ids);
        modelAndView.addObject("tripInfo", dailyTripPlanService.tripWithDailyPlan(tripService.findById(ids)));
        modelAndView.addObject("dailyPlanForm", new DailyTripPlan());
        modelAndView.setViewName("trip/show-managment-trip-plan-trip");
        return modelAndView;
    }

    @PostMapping(value = "/myTripManagment{ids}")
    public ModelAndView changeTripMemberStatus(@ModelAttribute TripsMemberWrapper form, BindingResult result, @RequestParam("ids") int ids) {
        ModelAndView modelAndView = new ModelAndView();
        tripService.tripMemberListUpdate(form.getTripMemberList());
        modelAndView.addObject("tripInfo", dailyTripPlanService.tripWithDailyPlan(tripService.findById(ids)));
        modelAndView.addObject("form", tripService.tripsMemberWrapper(ids));
        modelAndView.addObject("commentsForm", tripService.commentsWrapper(ids));
        modelAndView.setViewName("trip/show-managment-trip-users");
        return modelAndView;
    }

    @GetMapping(value = "/myTripManagment/comments{ids}")
    public ModelAndView CommentAnswerController(@RequestParam("ids") int ids) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("tripInfo", dailyTripPlanService.tripWithDailyPlan(tripService.findById(ids)));
        modelAndView.addObject("form", tripService.tripsMemberWrapper(ids));
        modelAndView.addObject("commentsForm", tripService.commentsWrapper(ids));
        modelAndView.setViewName("trip/show-managment-trip-comments");
        return modelAndView;
    }

    @PostMapping(value = "/myTripManagment/comments{ids}")
    public ModelAndView CommentAnswerController(@ModelAttribute CommentsWrapper commentsForm, BindingResult result, @RequestParam("ids") int ids) {
        ModelAndView modelAndView = new ModelAndView();
        tripService.commentsListUpdateByOwner(commentsForm.getCommentsList());
        modelAndView.addObject("tripInfo", dailyTripPlanService.tripWithDailyPlan(tripService.findById(ids)));
        modelAndView.addObject("form", tripService.tripsMemberWrapper(ids));
        modelAndView.addObject("commentsForm", tripService.commentsWrapper(ids));
        modelAndView.setViewName("trip/show-managment-trip-comments");
        return modelAndView;
    }
}
