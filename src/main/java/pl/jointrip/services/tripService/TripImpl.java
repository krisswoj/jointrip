package pl.jointrip.services.tripService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.jointrip.dao.CommentsRepository;
import pl.jointrip.dao.TripRepository;
import pl.jointrip.dao.UserRepository;
import pl.jointrip.models.Comments;
import pl.jointrip.models.Trip;
import pl.jointrip.models.User;
import pl.jointrip.services.userService.UserService;

import java.util.Date;
import java.util.List;

@Service
public class TripImpl implements TripService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    TripRepository tripRepository;
    @Autowired
    CommentsRepository commentsRepository;
    @Autowired
    UserService userService;

    @Value("${TRIP_ADDED_POSITIVE}")
    private String tripPositive;

    @Value("${TRIP_ADDED_NEGATIVE}")
    private String tripNegative;

    @Value("${COMMENT_ADDED_POSITIVE}")
    private String commentPositive;

    @Value("${COMMENT_ADDED_NEGATIVE}")
    private String commentNegative;

    @Value("${MEMBER_JOINED_TRIP_POSITIVE}")
    private String tripJoinPositive;

    @Value("${MEMBER_JOINED_TRIP_NEGATIVE}")
    private String tripJoinNegative;


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
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean joinToTripByUser(int id) {

        User user = userService.getLoggedUser();
        Trip trip = tripRepository.findById(id);

        if (tripRepository.findById(id).getUserByUserId().getUserId() == user.getUserId())
            return false;

        try {
            trip.getTripMembers().add(user);
            tripRepository.save(trip);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String addedTripNotification(Trip trip){
        return (saveTrip(trip)) ? tripPositive : tripNegative;
    }

    @Override
    public String addedCommentNotification(Comments comment, int tripId){
        return (saveComment(comment, tripId)) ? commentPositive : commentNegative;
    }

    @Override
    public String joinedTripNotification(int id){
        return (joinToTripByUser(id)) ? tripJoinPositive : tripJoinNegative;
    }

    @Override
    public List<Trip> joinedTripsByUser() {
        User user = userService.getLoggedUser();
        return tripRepository.findTripByTripMembers(user);
    }

    @Override
    public List<Trip> findTripByTripMembersNot() {
        User user = userService.getLoggedUser();
        return tripRepository.findTripByTripMembersNotContains(user);
    }
}
