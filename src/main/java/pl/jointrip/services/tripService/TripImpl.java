package pl.jointrip.services.tripService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.jointrip.dao.CommentsRepository;
import pl.jointrip.dao.TripMemberRepository;
import pl.jointrip.dao.TripRepository;
import pl.jointrip.dao.UserRepository;
import pl.jointrip.models.Comments;
import pl.jointrip.models.Trip;
import pl.jointrip.models.TripMember;
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
    @Autowired
    TripMemberRepository tripMemberRepository;

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

        User loggedUser = userService.getLoggedUser();
        trip.setTripCreateDate(new Date());
        trip.setTripEditDate(new Date());
        trip.setTripStatus(0);
        trip.setUserByUserId(loggedUser);

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

        User loggedUser = userService.getLoggedUser();
        comment.setUserId(loggedUser.getUserId());
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

        User loggedUser = userService.getLoggedUser();
        Trip trip = tripRepository.findById(id);

        if (tripRepository.findById(id).getUserByUserId().getUserId() == loggedUser.getUserId())
            return false;

        try {
            trip.getTripMembers().add(addNewMember(trip));
            tripRepository.save(trip);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public TripMember addNewMember(Trip trip){
        User loggedUser = userService.getLoggedUser();

        TripMember tripMember = new TripMember();
        tripMember.setTripMember(loggedUser);
        tripMember.setStatus(1);
        tripMember.setTrip(trip);
        return tripMember;
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
        User loggedUser = userService.getLoggedUser();
        return tripRepository.findTripByTripMembersContains(loggedUser);

    }

    @Override
    public List<Trip> findTripByTripMembersNot() {
        User loggedUser = userService.getLoggedUser();
        return tripRepository.findTripByTripMembersNotContains(loggedUser);
    }

    @Override
    public  List<Trip>findTripByUserByUserId(){
        User loggedUser = userService.getLoggedUser();
        return tripRepository.findTripByUserByUserId(loggedUser);
    }
}
