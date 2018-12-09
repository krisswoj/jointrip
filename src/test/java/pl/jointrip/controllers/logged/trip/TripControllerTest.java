package pl.jointrip.controllers.logged.trip;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pl.jointrip.dao.TripRepository;
import pl.jointrip.models.Comments;
import pl.jointrip.models.Trip;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class TripControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private TripRepository tripRepository;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }

    @WithMockUser(username = "admin@gmail.com", password = "qwe123")
    @Test
    public void showTrips() throws Exception{
        MvcResult result = mockMvc.perform(get("/showTrips"))
                .andExpect(status().isOk())
                .andReturn();

        Map<String,Object> modelMap = result.getModelAndView().getModel();
        List<Trip> trips = (List<Trip>) modelMap.get("show_trips");
        assertNotNull(trips);
        assertEquals("trip/trips", result.getModelAndView().getViewName());
    }

    @WithMockUser(username = "admin@gmail.com", password = "qwe123")
    @Test
    public void showTrip() throws Exception{
        MvcResult result = mockMvc.perform(get("/showTrip")
                .param("ide", "1"))
                .andExpect(status().isOk())
                .andReturn();

        Map<String,Object> modelMap = result.getModelAndView().getModel();
        Trip trip = (Trip) modelMap.get("tripInfo");
        assertNotNull(trip);
        assertEquals("trip/show-trip", result.getModelAndView().getViewName());
    }

    @WithMockUser(username = "admin@gmail.com", password = "qwe123")
    @Test
    public void addCommentForm() throws Exception{
        //todo ogarnąć mockami
        Trip trip = new Trip();
        Comments comments = new Comments();
        trip = tripRepository.findById(3);
        Date date = new Date();
        comments.setTrip(trip);
        comments.setId(230);
        comments.setStatus(1);
        comments.setOrganisationAnswer("asd");
        comments.setUserQuestion("asd");
        comments.setAddedQuestionDate(date);
        comments.setAnswerDate(date);
        mockMvc.perform(post("/showTrip/addedComment")
                .param("ide","3")
                .flashAttr("comment", comments))
                .andExpect(status().isOk());
    }

}