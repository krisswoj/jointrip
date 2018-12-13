package pl.jointrip.controllers.logged.trip;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import pl.jointrip.dao.CommentsRepository;
import pl.jointrip.dao.RoleRepository;
import pl.jointrip.dao.TripRepository;
import pl.jointrip.dao.UserRepository;
import pl.jointrip.models.entities.comments.Comments;
import pl.jointrip.models.entities.trip.Trip;
import pl.jointrip.models.entities.trip.TripMember;
import pl.jointrip.models.entities.user.User;
import pl.jointrip.services.tripService.impl.TripImpl;
import pl.jointrip.services.userService.impl.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
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
    private User user2;
    @Mock
    private Trip trip;
    @Mock
    private Trip trip2;
    @Mock
    private Trip trip3;
    @Mock
    private Comments comments;
    @Mock
    private Comments comments2;
    @Mock
    private TripMember tripMember;

    @Before
    public void setup() {
        user.setUserId(1);
        user.setEmail("admin@gmail.com");
        user.setPassword("qwe123");

        user.setUserId(2);
        user.setEmail("user@gmail.com");
        user.setPassword("qwe123");

        trip.setId(1);
        trip.setTripStatus(1);
        trip.setOrganizatorEmail("admin@gmail.com");
        trip.setTripTitle("Wyjazd do Norwegii");

        trip2.setId(2);
        trip2.setTripStatus(1);
        trip2.setOrganizatorEmail("user@gmail.com");
        trip2.setTripTitle("Wyjazd do Japonii");

        tripMember.setId(1);
        tripMember.setStatus(1);
        tripMember.setTrip(trip);
        tripMember.setTripMember(user);

        List<TripMember> tripMembers = new ArrayList<>();
        tripMembers.add(tripMember);

        trip3.setId(3);
        trip3.setTripStatus(2);
        trip3.setOrganizatorEmail("michal@wp.pl");
        trip3.setTripTitle("Dookola swiata");
        trip3.setTripMembers(tripMembers);
        trip3.setUserByUserId(user);

        comments.setId(1);
        comments.setStatus(1);
        comments.setUserQuestion("Czy mozna wziac zwierzaka?");
        comments.setOrganisationAnswer("Nie");

        comments2.setId(2);
        comments2.setStatus(1);
        comments2.setUserQuestion("Czy moge zabrac ze soba dzieci?");
        comments2.setOrganisationAnswer("Tak");

        MockitoAnnotations.initMocks(this);
    }

    @WithMockUser(username = "admin@gmail.com", password = "qwe123")
    @Test
    public void showTrips() {
        List<Trip> trips = new ArrayList<>();
        trips.add(trip);
        trips.add(trip2);

        when(userRepository.findByEmail(user.getEmail())).thenReturn(user);
        when(userServiceImpl.getLoggedUser()).thenReturn(user);
        when(tripRepository.findTripByTripMembersNotContains(user)).thenReturn(trips);

        assertEquals(trips,tripImpl.findTripByTripMembersNot());

        verify(tripRepository, times(1)).findTripByTripMembersNotContains(any());
    }

    @WithMockUser(username = "admin@gmail.com", password = "qwe123")
    @Test
    public void showTripsForUnlogged() {
        List<Trip> trips = new ArrayList<>();
        trips.add(trip);
        trips.add(trip2);

        when(tripRepository.findTripByTripStatus(1)).thenReturn(trips);

        assertEquals(trips,tripImpl.findAllActiveTrips());
        assertEquals(trip.getTripStatus(),tripImpl.findAllActiveTrips().get(0).getTripStatus());

        verify(tripRepository, times(2)).findTripByTripStatus(anyInt());
    }

    @WithMockUser(username = "admin@gmail.com", password = "qwe123")
    @Test
    public void showTrip() {
        List<Comments> commentsList = new ArrayList<>();
        commentsList.add(comments);
        commentsList.add(comments2);

        when(userRepository.findByEmail(user.getEmail())).thenReturn(user);
        when(userServiceImpl.getLoggedUser()).thenReturn(user);
        when(tripRepository.findById(2)).thenReturn(trip2);
        assertEquals(tripImpl.findById(2).getId(), tripRepository.findById(2).getId());
        when(tripRepository.existsTripByTripMembers(trip2, user)).thenReturn(true);
        assertEquals(tripImpl.existsTripByTripMembers(trip2,user), tripRepository.existsTripByTripMembers(trip2, user));
        assertNotEquals(false,tripImpl.existsTripByTripMembers(trip2,user));
        when(commentsRepository.findByTripAndStatusIs(trip2, 1)).thenReturn(commentsList);
        assertEquals(tripImpl.findByTripAndStatusIs(trip2,1),commentsRepository.findByTripAndStatusIs(trip2,1));

    }

    @WithMockUser(username = "admin@gmail.com", password = "qwe123")
    @Test
    public void addCommentForm() {
        List<Comments> commentsList = new ArrayList<>();
        commentsList.add(comments);

        when(tripRepository.findById(1)).thenReturn(trip);
        assertEquals(tripImpl.findById(1), tripRepository.findById(1));
        when(commentsRepository.findByTripAndStatusIs(trip3, 2)).thenReturn(commentsList);
        assertEquals(tripImpl.findByTripAndStatusIs(trip3,2),commentsRepository.findByTripAndStatusIs(trip3,2));
    }

    @WithMockUser(username = "admin@gmail.com", password = "qwe123")
    @Test
    public void joinToTrip() {
        List<Comments> commentsList = new ArrayList<>();
        commentsList.add(comments);
        commentsList.add(comments2);

        when(tripRepository.findById(2)).thenReturn(trip2);
        assertEquals(tripImpl.findById(2), tripRepository.findById(2));
        when(commentsRepository.findByTripAndStatusIs(trip, 1)).thenReturn(commentsList);
        assertEquals(tripImpl.findByTripAndStatusIs(trip,1),commentsRepository.findByTripAndStatusIs(trip,1));



        when(userRepository.findByEmail(user.getEmail())).thenReturn(user);
        when(userServiceImpl.getLoggedUser()).thenReturn(user);
        when(tripRepository.findById(3)).thenReturn(trip3);
        when(tripRepository.existsTripByTripMembers(trip, user)).thenReturn(false);
    }

}