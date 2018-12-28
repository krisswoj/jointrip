package pl.jointrip.controllers.logged.adminPanel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.jointrip.models.entities.trip.Trip;
import pl.jointrip.models.system.SystemNotification;
import pl.jointrip.services.tripService.TripAcceptationService;

@Controller
public class TripAcceptationController {

    @Value("${TRIP_ACCEPTED_POSITIVE}")
    private String acceptTripPositive;

    @Value("${TRIP_ACCEPTED_NEGATIVE}")
    private String acceptTripNegative;

    @Value("${TRIP_REJECTED_POSITIVE}")
    private String rejectTripPositive;

    @Value("${TRIP_REJECTED_NEGATIVE}")
    private String rejectTripNegative;

    @Autowired
    TripAcceptationService acceptationService;

    @RequestMapping(value = "/admin/acceptationPanel", method = RequestMethod.GET)
    public ModelAndView showTripsToAccept() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("notAcceptedTrips", acceptationService.fetchTripsToActivate(0));
        mv.setViewName("admin/acceptation-panel");
        return mv;
    }

    @RequestMapping(value = "/admin/acceptationPanel/accept", params = "id", method = RequestMethod.GET)
    public ModelAndView acceptTrip(@RequestParam("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        boolean result = acceptationService.changeTripStatus(id, 1);
        SystemNotification systemNotification = result ? new SystemNotification("true", acceptTripPositive) : new SystemNotification("fail", acceptTripNegative);
        modelAndView.addObject("message", systemNotification);
        modelAndView.addObject("notAcceptedTrips", acceptationService.fetchTripsToActivate(0));
        modelAndView.setViewName("admin/acceptation-panel");
        return modelAndView;
    }
    @RequestMapping(value = "/admin/acceptationPanel/show", params = "id", method = RequestMethod.GET)
    public ModelAndView showTrip(@RequestParam("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        Trip trip = acceptationService.fetchTripById(id);
        modelAndView.addObject("tripInfo", trip);
        modelAndView.addObject("members", trip.getTripMembers());
        modelAndView.setViewName("admin/trip-info");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/acceptationPanel/reject", params = "id", method = RequestMethod.GET)
    public ModelAndView rejectTrip(@RequestParam("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        boolean result = acceptationService.changeTripStatus(id, 2);
        SystemNotification systemNotification = result ? new SystemNotification("true", rejectTripPositive) : new SystemNotification("fail", rejectTripNegative);
        modelAndView.addObject("message", systemNotification);
        modelAndView.addObject("notAcceptedTrips", acceptationService.fetchTripsToActivate(0));
        modelAndView.setViewName("admin/acceptation-panel");
        return modelAndView;
    }
}
