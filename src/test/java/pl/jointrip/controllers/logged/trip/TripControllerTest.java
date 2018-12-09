package pl.jointrip.controllers.logged.trip;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pl.jointrip.models.Trip;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import java.util.List;
import java.util.Map;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class TripControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Resource
    private FilterChainProxy springSecurityFilterChain;

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
    }

    @Test
    public void editUser() {
    }

    @Test
    public void fetchUserInfo() {
    }
}