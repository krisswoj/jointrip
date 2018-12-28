package pl.jointrip.controllers.logged.trip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import pl.jointrip.models.entities.comments.CommentsWrapper;
import pl.jointrip.models.entities.documents.ImagesStore;
import pl.jointrip.models.entities.trip.DailyTripPlan;
import pl.jointrip.models.entities.trip.TripsMemberWrapper;
import pl.jointrip.models.system.SystemNotification;
import pl.jointrip.services.imagesUploadServices.ImagesService;
import pl.jointrip.services.tripService.DailyTripPlanService;
import pl.jointrip.services.tripService.TripService;

import javax.validation.Valid;
import java.io.File;

@Controller
public class OrganizerTripController {

    @Autowired
    TripService tripService;

    @Autowired
    ImagesService imagesService;

    @Autowired
    DailyTripPlanService dailyTripPlanService;

    @Value("${USER_STATUS_CHANGED_POSITIVE}")
    private String userPositive;

    @Value("${USER_STATUS_CHANGED_NEGATIVE}")
    private String userNegative;

    @GetMapping(value = "/myTripsManagment")
    public ModelAndView tripsManagmentList() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("managmentTrips", tripService.tripMapWithStatisticForOrganisator());
        modelAndView.setViewName("trip/managment-trips");
        return modelAndView;
    }

    @GetMapping(value = "/myTripManagment{ids}")
    public ModelAndView getCourseDetails(@RequestParam("ids") int ids) {
        ModelAndView modelAndView = mavWithTripInfoFormCommentsForm(ids);
        modelAndView.setViewName("trip/show-managment-trip-main");
        return modelAndView;
    }

    @GetMapping(value = "/myTripManagment/users{ids}")
    public ModelAndView getCourseDetailsUsers(@RequestParam("ids") int ids) {
        ModelAndView modelAndView = mavWithTripInfoFormCommentsForm(ids);
        modelAndView.setViewName("trip/show-managment-trip-users");
        return modelAndView;
    }

    @GetMapping(value = "/myTripManagment/travelerPanel{ids}")
    public ModelAndView travelerPanelOrganisator(@RequestParam("ids") int ids) {
        ModelAndView modelAndView = mavWithTripInfoAndDailyPlanForm(ids);
        modelAndView.setViewName("trip/show-managment-trip-plan-trip");
        return modelAndView;
    }

    @PostMapping(value = "/myTripManagment/travelerPanel{ids}")
    public ModelAndView travelerPanelOrganisatorAddNewPlan(@ModelAttribute DailyTripPlan dailyTripPlan, @RequestParam("ids") int ids) {
        ModelAndView modelAndView = mavWithTripInfoAndDailyPlanForm(ids);
        dailyTripPlanService.addNewDailyPlan(dailyTripPlan, ids);
        modelAndView.setViewName("trip/show-managment-trip-plan-trip");
        return modelAndView;
    }

    @PostMapping(value = "/myTripManagment{ids}")
    public ModelAndView changeTripMemberStatus(@ModelAttribute TripsMemberWrapper form, BindingResult result, @RequestParam("ids") int ids) {
        tripService.tripMemberListUpdate(form.getTripMemberList());
        ModelAndView modelAndView = mavWithTripInfoFormCommentsForm(ids);
        boolean result1 = true;
        SystemNotification systemNotification = result1 ? new SystemNotification("true", userPositive) : new SystemNotification("fail", userNegative);
        modelAndView.addObject("message", systemNotification);
        modelAndView.setViewName("trip/show-managment-trip-users");
        return modelAndView;
    }

    @GetMapping(value = "/myTripManagment/comments{ids}")
    public ModelAndView CommentAnswerController(@RequestParam("ids") int ids) {
        ModelAndView modelAndView = mavWithTripInfoFormCommentsForm(ids);
        modelAndView.setViewName("trip/show-managment-trip-comments");
        return modelAndView;
    }

    @PostMapping(value = "/myTripManagment/comments{ids}")
    public ModelAndView CommentAnswerController(@ModelAttribute CommentsWrapper commentsForm, BindingResult result, @RequestParam("ids") int ids) {
        tripService.commentsListUpdateByOwner(commentsForm.getCommentsList());
        ModelAndView modelAndView = mavWithTripInfoFormCommentsForm(ids);
        modelAndView.setViewName("trip/show-managment-trip-comments");
        return modelAndView;
    }

    @GetMapping(value = "/myTripManagment/gallery{ids}")
    public ModelAndView travellerPanelGallery(@RequestParam("ids") int ids) {
        ModelAndView modelAndView = mavWithTripInfoAndDailyPlanForm(ids);
        modelAndView.addObject("galleryForm", new ImagesStore());
        modelAndView.setViewName("trip/show-managment-trip-gallery");
        return modelAndView;
    }

    @PostMapping(value = "/myTripManagment/gallery{ids}")
    public ModelAndView travellerPanelGallery(@Valid ImagesStore imagesStore, BindingResult result, @RequestParam("file") MultipartFile file, @RequestParam("ids") int ids){
        ModelAndView modelAndView = mavWithTripInfoAndDailyPlanForm(ids);
        imagesService.saveImage(file, tripService.findById(ids), imagesStore);
        modelAndView.addObject("galleryForm", new ImagesStore());
        modelAndView.setViewName("trip/show-managment-trip-gallery");
        return modelAndView;
    }

    private ModelAndView mavWithTripInfoFormCommentsForm(int tripId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("tripInfo", dailyTripPlanService.tripWithDailyPlan(tripService.findById(tripId)));
        modelAndView.addObject("form", tripService.tripsMemberWrapper(tripId));
        modelAndView.addObject("commentsForm", tripService.commentsWrapper(tripId));
        return modelAndView;
    }

    private ModelAndView mavWithTripInfoAndDailyPlanForm(int tripId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("tripInfo", dailyTripPlanService.tripWithDailyPlan(tripService.findById(tripId)));
        modelAndView.addObject("dailyPlanForm", new DailyTripPlan());
        return modelAndView;
    }
}