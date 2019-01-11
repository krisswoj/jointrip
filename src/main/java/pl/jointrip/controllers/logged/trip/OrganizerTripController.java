package pl.jointrip.controllers.logged.trip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import pl.jointrip.dao.TripExtraCostsRepository;
import pl.jointrip.dao.TripRepository;
import pl.jointrip.dao.UserRepository;
import pl.jointrip.models.entities.comments.CommentsWrapper;
import pl.jointrip.models.entities.documents.Documentstore;
import pl.jointrip.models.entities.documents.ImagesStore;
import pl.jointrip.models.entities.trip.DailyTripPlan;
import pl.jointrip.models.entities.trip.Trip;
import pl.jointrip.models.entities.trip.TripExtraCosts;
import pl.jointrip.models.entities.trip.TripsMemberWrapper;
import pl.jointrip.models.system.SystemNotification;
import pl.jointrip.models.viewModels.documents.DocumentsApprovalViewModel;
import pl.jointrip.services.documentsService.DocumentsService;
import pl.jointrip.services.imagesUploadServices.ImagesService;
import pl.jointrip.services.tripService.DailyTripPlanService;
import pl.jointrip.services.tripService.TripService;
import pl.jointrip.services.userService.UserService;

import javax.validation.Valid;
import java.io.File;

@Controller
public class OrganizerTripController {

    private TripService tripService;
    private UserRepository userRepository;
    private ImagesService imagesService;
    private DailyTripPlanService dailyTripPlanService;
    private TripExtraCostsRepository tripExtraCostsRepository;
    private DocumentsService documentsService;
    private UserService userService;
    private TripRepository tripRepository;

    @Value("${USER_STATUS_CHANGED_POSITIVE}")
    private String userPositive;

    @Value("${USER_STATUS_CHANGED_NEGATIVE}")
    private String userNegative;

    @Autowired
    public OrganizerTripController(TripService tripService, UserRepository userRepository, ImagesService imagesService, DailyTripPlanService dailyTripPlanService, TripExtraCostsRepository tripExtraCostsRepository, DocumentsService documentsService, UserService userService, TripRepository tripRepository) {
        this.tripService = tripService;
        this.userRepository = userRepository;
        this.imagesService = imagesService;
        this.dailyTripPlanService = dailyTripPlanService;
        this.tripExtraCostsRepository = tripExtraCostsRepository;
        this.documentsService = documentsService;
        this.userService = userService;
        this.tripRepository = tripRepository;
    }

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
    public ModelAndView travellerPanelGallery(@Valid ImagesStore imagesStore, BindingResult result, @RequestParam("file") MultipartFile file, @RequestParam("ids") int ids) {
        ModelAndView modelAndView = mavWithTripInfoAndDailyPlanForm(ids);
        imagesService.saveImage(file, tripService.findById(ids), imagesStore);
        modelAndView.addObject("galleryForm", new ImagesStore());
        modelAndView.setViewName("trip/show-managment-trip-gallery");
        return modelAndView;
    }

    @GetMapping(value = "/myTripManagment/files-to-download{ids}")
    public ModelAndView travellerFilesToDownload(@RequestParam("ids") int ids) {
        ModelAndView modelAndView = mavWithTripInfoAndDailyPlanForm(ids);
        modelAndView.addObject("filesForm", new DocumentsApprovalViewModel());
        modelAndView.setViewName("trip/show-managment-trip-files-to-download");
        return modelAndView;
    }

    @PostMapping(value = "/myTripManagment/files-to-download{ids}")
    public ModelAndView travellerFilesToDownload(@Valid DocumentsApprovalViewModel documentsApprovalViewModel, @RequestParam("ids") int ids) {
        documentsApprovalViewModel.setLoggedUser(userService.getLoggedUser());
        documentsApprovalViewModel.setTrip(tripService.findById(ids));
        documentsService.saveDocument(documentsApprovalViewModel);
        ModelAndView modelAndView = mavWithTripInfoAndDailyPlanForm(ids);
        modelAndView.addObject("filesForm", new DocumentsApprovalViewModel());
        modelAndView.setViewName("trip/show-managment-trip-files-to-download");
        return modelAndView;
    }

    @GetMapping(value = "/myTripManagment/extra-costs{ids}")
    public ModelAndView travellerExtraCosts(@RequestParam("ids") int ids) {
        ModelAndView modelAndView = mavWithTripInfoAndDailyPlanForm(ids);
        modelAndView.addObject("costsForm", new TripExtraCosts());
        modelAndView.setViewName("trip/show-managment-trip-extra-costs");
        return modelAndView;
    }

    @GetMapping(value = "/myTripManagment/chat/{trip}/{member}")
    public ModelAndView travellerChatWithMember(@PathVariable("trip") int tripId, @PathVariable("member") int memberId) {
        return mavtravellerChatWithMember(tripId, memberId);
    }

    @PostMapping(value = "/myTripManagment/chat/{trip}/{member}")
    public ModelAndView travellerChatWithMemberForm(@RequestParam("chatMessage") String chatMessage, @PathVariable("trip") int tripId, @PathVariable("member") int memberId) {
        tripService.chatTripAddMessage(tripRepository.findById(tripId), userRepository.findByUserId(memberId), chatMessage, 2);
        return mavtravellerChatWithMember(tripId, memberId);
    }

    @PostMapping(value = "/myTripManagment/extra-costs{ids}")
    public ModelAndView travellerExtraCostsForm(@ModelAttribute("costsForm") TripExtraCosts tripExtraCosts, @RequestParam("ids") int ids) {
        ModelAndView modelAndView = mavWithTripInfoAndDailyPlanForm(ids);
        tripExtraCosts.setTripId(tripService.findById(ids));
        tripExtraCostsRepository.save(tripExtraCosts);
        modelAndView.addObject("costsForm", new TripExtraCosts());
        modelAndView.setViewName("trip/show-managment-trip-extra-costs");
        return modelAndView;
    }

    @GetMapping(value = "/myTripManagment/edit", params = "ids")
    public ModelAndView editTrip(@RequestParam("ids") int tripId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("tripEdit", tripService.findById(tripId));
        modelAndView.setViewName("trip/edit");
        return modelAndView;
    }

    @PostMapping(value = "/myTripManagment/edit", params = "ids")
    public ModelAndView editTrip(@Valid Trip trip, @RequestParam("ids") int tripId) {
        Trip tripEdit = tripService.findById(tripId);

        tripEdit.setTripTitle(trip.getTripTitle());
        tripEdit.setTripShortDesc(trip.getTripShortDesc());
        tripEdit.setTripFullDesc(trip.getTripFullDesc());
        tripEdit.setTripStartDate(trip.getTripStartDate());
        tripEdit.setTripEndDate(trip.getTripEndDate());
        tripEdit.setTripCountry(trip.getTripCountry());
        tripEdit.setTripCity(trip.getTripCity());
        tripEdit.setTripStreet(trip.getTripStreet());
        tripEdit.setOrganizatorPhoneNumber(trip.getOrganizatorPhoneNumber());

        tripService.editTrip(tripEdit);
        ModelAndView modelAndView = new ModelAndView("redirect:/myTripManagment?ids=" + tripId);
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

    private ModelAndView mavtravellerChatWithMember(int tripId, int memberId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("memberId", memberId);
        modelAndView.addObject("tripInfo", dailyTripPlanService.tripWithDailyPlan(tripService.findById(tripId)));
        modelAndView.addObject("tripContent", dailyTripPlanService.tripWithDailyPlan(dailyTripPlanService.findTripByIdAndByUserMember(tripId, memberId)));
        modelAndView.setViewName("trip/show-managment-trip-chat");
        return modelAndView;
    }

}