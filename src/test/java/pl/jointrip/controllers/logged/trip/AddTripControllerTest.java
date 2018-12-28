package pl.jointrip.controllers.logged.trip;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.jointrip.dao.TripRepository;
import pl.jointrip.dao.UserRepository;
import pl.jointrip.models.entities.trip.Trip;
import pl.jointrip.models.entities.user.User;
import pl.jointrip.services.tripService.impl.TripServiceImpl;
import pl.jointrip.services.userService.impl.UserServiceImpl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class AddTripControllerTest {

    @InjectMocks
    private TripServiceImpl tripServiceImpl;
    @Mock
    private TripRepository tripRepository;
    @Mock
    private UserServiceImpl userServiceImpl;
    @Mock
    private UserRepository userRepository;
    @Mock
    private User user;
    @Mock
    private Trip trip;

    @Before
    public void setUp() throws Exception {

        user.setUserId(1);
        user.setEmail("admin@gmail.com");
        user.setPassword("qwe123");

        trip.setId(1);
        trip.setTripStatus(1);
        trip.setOrganizatorEmail("admin@gmail.com");
        trip.setTripTitle("Wyjazd do Norwegii");

        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void addTripForm() {

        when(userRepository.findByEmail(user.getEmail())).thenReturn(user);
        when(tripRepository.save(trip)).thenReturn(trip);
        when(userServiceImpl.getLoggedUser()).thenReturn(user);
        when(tripServiceImpl.saveTrip(trip)).thenReturn(true);


        assertEquals(tripServiceImpl.addedTripNotification(trip),tripServiceImpl.saveTrip(trip));

    }
}