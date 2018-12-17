package pl.jointrip.controllers.logged.adminPanel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.jointrip.services.tripService.TripAcceptationService;
import pl.jointrip.services.tripService.TripService;

@Controller
public class AllTripsController {

    @Autowired
    private TripService tripService;
    @Autowired
    private TripAcceptationService acceptationService;

    @GetMapping(value = "admin/allTripsPanel")
    public ModelAndView fetchTrips(){
        ModelAndView modelAndView =  new ModelAndView();
        modelAndView.addObject("trips", tripService.findAllActiveTripsForNoLogUser());
        modelAndView.setViewName("admin/allTripsPanel");
        return modelAndView;
    }

    @GetMapping(value = "/admin/allTripsPanel/block", params = "id")
    public ModelAndView blockTrip(@RequestParam("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        acceptationService.changeTripStatus(id, 2);
        modelAndView.addObject("trips", tripService.findAllActiveTripsForNoLogUser());
        modelAndView.setViewName("admin/allTripsPanel");
        return modelAndView;
    }
    @GetMapping(value = "/admin/allTripsPanel/remove", params = "id")
    public ModelAndView removeTrip(@RequestParam("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        boolean result = tripService.removeTrip(id);
        modelAndView.addObject("trips", tripService.findAllActiveTripsForNoLogUser());
        modelAndView.setViewName("admin/allTripsPanel");
        return modelAndView;
    }
}
