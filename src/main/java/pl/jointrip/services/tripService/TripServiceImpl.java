package pl.jointrip.services.tripService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pl.jointrip.dao.CommentsRepository;
import pl.jointrip.dao.TripRepository;
import pl.jointrip.dao.UserRepository;
import pl.jointrip.models.Comments;
import pl.jointrip.models.Trip;
import pl.jointrip.models.User;
import pl.jointrip.services.userService.UserService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TripServiceImpl implements TripService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    TripRepository tripRepository;
    @Autowired
    CommentsRepository commentsRepository;
    @Autowired
    UserService userService;

    @Override
    public boolean saveTrip(Trip trip) {

        trip.setTripCreateDate(new Date());
        trip.setTripEditDate(new Date());
        trip.setTripStatus(0);
        trip.setUserByUserId(userService.getLoggedUser());
        try {
            tripRepository.save(trip);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean saveComment(Comments comment, int tripId) {

        User user = userService.getLoggedUser();

        comment.setUserId(user.getUserId());
        comment.setTripId(tripId);
        try {
            commentsRepository.save(comment);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean joinToTripByUser(int id) {

        User user = userService.getLoggedUser();

        if (tripRepository.findById(id).getUserByUserId().getUserId() != user.getUserId()) {
            Trip trip = tripRepository.findById(id);
            try {
                trip.getTripMembers().add(user);
                tripRepository.save(trip);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public List<Trip> joinedTripByUser() {
        User user = userService.getLoggedUser();

        Iterable<Trip> allTrips = tripRepository.findAll();

        List<Trip> tripsWhereUserIsAMember = new ArrayList<>();

        for (Trip signleTrip : allTrips) {
            for (User userInfo : signleTrip.getTripMembers()) {
                if (user.getUserId() == userInfo.getUserId()) {
                    tripsWhereUserIsAMember.add(signleTrip);
                }
            }
        }
        return tripsWhereUserIsAMember;
    }
}
