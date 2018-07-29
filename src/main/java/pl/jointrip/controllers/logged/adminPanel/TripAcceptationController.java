package pl.jointrip.controllers.logged.adminPanel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.jointrip.dao.TripRepository;
import pl.jointrip.models.TripEntity;

@Controller
public class TripAcceptationController {

    @Autowired
    TripRepository tripRepository;

    @RequestMapping(value = "/admin/acceptationPanel", method = RequestMethod.GET)
    public ModelAndView showTripsToAccept(){
        ModelAndView mv = new ModelAndView();
        Iterable<TripEntity> trips = tripRepository.findAll();
        mv.addObject("notAcceptedTrips", trips);
        mv.setViewName("admin/acceptationPanel");
        return mv;
    }
}
