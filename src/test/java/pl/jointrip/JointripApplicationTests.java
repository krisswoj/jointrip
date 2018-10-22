package pl.jointrip;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.jointrip.configuration.SecurityConfiguration;
import pl.jointrip.configuration.WebMvcConfig;
import pl.jointrip.services.tripService.TripService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SecurityConfiguration.class, WebMvcConfig.class})
public class JointripApplicationTests {

    @Autowired
    TripService tripService;

//    @Test
//    public void restTrips


}
