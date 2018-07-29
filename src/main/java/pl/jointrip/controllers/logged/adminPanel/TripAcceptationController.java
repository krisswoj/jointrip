package pl.jointrip.controllers.logged.adminPanel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.jointrip.dao.TripRepository;
import pl.jointrip.models.Trip;

@Controller
public class TripAcceptationController {

    @Autowired
    TripRepository tripRepository;

    @RequestMapping(value = "/admin/acceptationPanel", method = RequestMethod.GET)
    public ModelAndView showTripsToAccept() {
        ModelAndView mv = new ModelAndView();
        Iterable<Trip> trips = tripRepository.findTripByTripStatus(0);
        mv.addObject("notAcceptedTrips", trips);
        mv.setViewName("admin/acceptationPanel");
        return mv;
    }

    @RequestMapping(value = "/admin/acceptationPanel/accept", params = "id", method = RequestMethod.GET)
    public ModelAndView acceptTrip(@RequestParam("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        Trip trip = tripRepository.findById(id);
        trip.setTripStatus(1);
        tripRepository.save(trip);
        Iterable<Trip> trips = tripRepository.findTripByTripStatus(0);
        modelAndView.addObject("notAcceptedTrips", trips);
        modelAndView.setViewName("admin/acceptationPanel");
        return modelAndView;
    }
    @RequestMapping(value = "/admin/acceptationPanel/show", params = "id", method = RequestMethod.GET)
    public ModelAndView showTrip(@RequestParam("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        Trip trip = tripRepository.findById(id);
        modelAndView.addObject("tripInfo", trip);
        modelAndView.setViewName("admin/tripInfo");
        return modelAndView;
    }
}
