package pl.jointrip;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import pl.jointrip.configuration.SecurityConfiguration;
import pl.jointrip.configuration.WebMvcConfig;
import pl.jointrip.dao.UserRepository;
import pl.jointrip.models.TripWrapper;
import pl.jointrip.services.tripService.TripImpl;
import pl.jointrip.services.tripService.TripService;
import pl.jointrip.services.userService.UserService;
import pl.jointrip.services.userService.UserServiceImpl;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {WebMvcConfig.class})
@Import(TripImpl.class)
public class JointripApplicationTests {

    @Autowired
    private TripImpl tripService;

//    @Autowired
//    UserService userService;
//
//    @Autowired
//    UserRepository userRepository;


    @Test
    public void testStatictics(){

        List<TripWrapper> tripWrapperList = tripService.tripWithStatistics();
        tripWrapperList.size();

    }


}
