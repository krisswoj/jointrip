package pl.jointrip.controllers.logged.trip;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import pl.jointrip.dao.CommentsRepository;
import pl.jointrip.dao.RoleRepository;
import pl.jointrip.dao.TripRepository;
import pl.jointrip.dao.UserRepository;
import pl.jointrip.models.Trip;
import pl.jointrip.models.User;
import pl.jointrip.services.tripService.TripImpl;
import pl.jointrip.services.userService.UserServiceImpl;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class TripControllerTest {

    @InjectMocks
    private TripImpl tripImpl;

    @Mock
    private UserServiceImpl userServiceImpl;
    @Mock
    private TripRepository tripRepository;
    @Mock
    private CommentsRepository commentsRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private User user;
    @Mock
    private Trip trip;
    @Mock
    private List<User> users;
    @Mock
    private List<Trip> trips;

    @Before
    public void setup() {
        user.setUserId(1);
        user.setEmail("admin@gmail.com");
        user.setPassword("qwe123");

        trip.setId(1);
        trip.setTripStatus(1);

        users.add(user);

        trips.add(trip);

        MockitoAnnotations.initMocks(this);
    }

    @WithMockUser(username = "admin@gmail.com", password = "qwe123")
    @Test
    public void showTrips() {

    }

    @WithMockUser(username = "admin@gmail.com", password = "qwe123")
    @Test
    public void showTrip() {
        when(userRepository.findByEmail(user.getEmail())).thenReturn(user);
        when(userServiceImpl.getLoggedUser()).thenReturn(user);
        when(tripRepository.findTripByTripMembersNotContains(user)).thenReturn(trips);

        assertEquals(trips,tripImpl.findTripByTripMembersNot());

        verify(tripRepository, times(1)).findTripByTripMembersNotContains(any());

    }

    @WithMockUser(username = "admin@gmail.com", password = "qwe123")
    @Test
    public void addCommentForm() {

    }

}