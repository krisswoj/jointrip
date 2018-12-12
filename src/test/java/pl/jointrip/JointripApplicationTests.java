package pl.jointrip;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import pl.jointrip.configuration.WebMvcConfig;
import pl.jointrip.models.TripWrapper;
import pl.jointrip.services.tripService.impl.TripImpl;

import java.util.List;

@Ignore
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
