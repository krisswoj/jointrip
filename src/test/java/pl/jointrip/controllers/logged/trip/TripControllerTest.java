package pl.jointrip.controllers.logged.trip;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pl.jointrip.dao.CommentsRepository;
import pl.jointrip.dao.RoleRepository;
import pl.jointrip.dao.TripRepository;
import pl.jointrip.dao.UserRepository;
import pl.jointrip.models.Comments;
import pl.jointrip.models.Trip;
import pl.jointrip.models.User;
import pl.jointrip.services.tripService.TripImpl;
import pl.jointrip.services.tripService.TripService;
import pl.jointrip.services.userService.UserService;
import pl.jointrip.services.userService.UserServiceImpl;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class TripControllerTest {


    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @InjectMocks
    private TripImpl tripImpl;

    @InjectMocks
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
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Mock
    private SecurityContextHolder securityContextHolder;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
        MockitoAnnotations.initMocks(this);
        Mockito.mock(Authentication.class);

    }

    @WithMockUser(username = "admin@gmail.com", password = "qwe123")
    @Test
    public void showTrips() throws Exception{
        MvcResult result = mockMvc.perform(get("/showTrips"))
                .andExpect(status().isOk())
                .andReturn();

        Trip trip = new Trip(3);
        Comments comments = new Comments();
        Date date = new Date();
        comments.setTrip(trip);
        comments.setStatus(0);
        comments.setOrganisationAnswer("");
        comments.setUserQuestion("asd");
        comments.setAddedQuestionDate(date);
        comments.setAnswerDate(date);

        Map<String,Object> modelMap = result.getModelAndView().getModel();
        List<Trip> trips1 = tripImpl.findTripByTripMembersNot();

        List<Trip> trips = (List<Trip>) modelMap.get("show_trips");
        assertEquals("trip/trips", result.getModelAndView().getViewName());
        assertNotNull(trips);
        //assertThat(trips.size(), greaterThan(0));
        //Mockito.when(tripRepository.findTripByTripMembersNotContains(new User(1))).thenReturn(List<Trip>);
    }

    @Test
    public void showTrip() throws Exception{
        /*MvcResult result = mockMvc.perform(get("/showTrip")
                .param("ide", "1"))
                .andExpect(status().isOk())
                .andReturn();
        Map<String,Object> modelMap = result.getModelAndView().getModel();
        Trip trip = (Trip) modelMap.get("tripInfo");
        assertEquals("trip/show-trip", result.getModelAndView().getViewName());
        assertNotNull(trip);*/

        List<Trip> trips = new ArrayList<>();
        User user = new User();

        when(userServiceImpl.getLoggedUser()).thenReturn(user);
        //tu w tej metodzie wywala nulla  Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();



        when(tripRepository.findTripByTripMembersNotContains(user)).thenReturn(trips);
        List<User> users = new ArrayList<>();
        assertEquals(users,tripImpl.findTripByTripMembersNot());
        verify(tripRepository, times(1)).findTripByTripMembersNotContains(any());

        //List<Trip> trips = tripImpl.findTripByTripMembersNot();
        //verify(tripRepository).findTripByTripMembersNotContains(user);

        //Mockito.when(tripRepository.findById(org.mockito.Matchers.anyInt())).thenReturn(trip);
        //assertEquals(trip1,tripRepository.findById(5));
    }

    @WithMockUser(username = "admin@gmail.com", password = "qwe123")
    @Test
    public void addCommentForm() throws Exception{
        /*//todo ogarnąć mockami
        //369b13e0
        Trip trip = new Trip(3);
        Comments comments = new Comments();
        Date date = new Date();
        comments.setTrip(trip);
        comments.setStatus(0);
        comments.setOrganisationAnswer("");
        comments.setUserQuestion("asd");
        comments.setAddedQuestionDate(date);
        comments.setAnswerDate(date);
        *//*mockMvc.perform(post("/showTrip/addedComment")
                .param("ide","3")
                .flashAttr("comment", comments))
                .andExpect(status().isOk());*//*
        Iterable<Comments> commentsList = commentsRepository.findAll();
        commentsRepository.save(comments);
        Iterable<Comments> commentsList1 = commentsRepository.findAll();
        commentsRepository.save(comments);
        Iterable<Comments> commentsList2 = commentsRepository.findAll();*/

        User user = new User(1);
        when(tripImpl.findTripByTripMembersNot()).thenCallRealMethod();
        assertEquals(new ArrayList<>(), tripImpl.findTripByTripMembersNot());


    }

}