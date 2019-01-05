package pl.jointrip.controllers.logged.trip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.jointrip.dao.TripRepository;
import pl.jointrip.services.tripService.DailyTripPlanService;
import pl.jointrip.services.tripService.TripService;
import pl.jointrip.services.userService.UserService;

@Controller
public class TravelersPanel {

    private TripRepository tripRepository;
    private UserService userService;
    private DailyTripPlanService dailyTripPlanService;
    private TripService tripService;

    @Autowired
    public TravelersPanel(TripRepository tripRepository, UserService userService, DailyTripPlanService dailyTripPlanService, TripService tripService) {
        this.tripRepository = tripRepository;
        this.userService = userService;
        this.dailyTripPlanService = dailyTripPlanService;
        this.tripService = tripService;
    }

    @GetMapping(value = "/travelerPanel{tripId}")
    public ModelAndView travelerUserPanel(@RequestParam("tripId") int tripId) {
        return travelerPanelMav(tripId);
    }

    @PostMapping(value = "/travelerPanel{tripId}")
    public ModelAndView travelerUserPanelForm(@RequestParam("chatMessage") String chatMessage, @RequestParam("tripId") int tripId) {
        tripService.chatTripAddMessage(tripRepository.findById(tripId), userService.getLoggedUser(), chatMessage, 1);
        return travelerUserPanel(tripId);
    }

    private ModelAndView travelerPanelMav(int tripId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("tripContent", dailyTripPlanService.tripWithDailyPlan(dailyTripPlanService.findTripById(tripId)));
        modelAndView.setViewName("trip/traveler-panel");
        return modelAndView;
    }
}