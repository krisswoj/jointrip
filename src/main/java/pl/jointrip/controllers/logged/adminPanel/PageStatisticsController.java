package pl.jointrip.controllers.logged.adminPanel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.jointrip.services.adminStats.AdminStatisticsService;

@Controller
public class PageStatisticsController {

    @Autowired
    AdminStatisticsService statisticsService;

    @GetMapping(value = "admin/pageStatistics")
    public ModelAndView fetchUsers() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("statistic", statisticsService.fetchStatisticsViewModel());
        modelAndView.setViewName("admin/pageStatistics");
        return modelAndView;
    }
}
