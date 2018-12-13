package pl.jointrip.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.jointrip.models.entities.trip.DailyTripPlan;
import pl.jointrip.services.tripService.TripService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DailyTripPlanRepositoryTest {

    @Autowired
    TripService tripService;

    @Autowired
    DailyTripPlanRepository dailyTripPlanRepository;

    @Test
    public void addTestPlan(){

        DailyTripPlan dailyTripPlan = new DailyTripPlan();
        dailyTripPlan.setTitle("wertrytj");
        dailyTripPlan.setDescription("dfghjgkyu");
        dailyTripPlan.setTripId(tripService.findById(10));
        dailyTripPlanRepository.save(dailyTripPlan);

    }

}