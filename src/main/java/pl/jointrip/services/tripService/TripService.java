package pl.jointrip.services.tripService;

import org.springframework.stereotype.Service;
import pl.jointrip.models.Comments;
import pl.jointrip.models.Trip;
import pl.jointrip.models.TripMember;

import java.util.List;

public interface TripService {

    boolean saveTrip(Trip trip);

    boolean saveComment(Comments comment, int tripId);

    boolean joinToTripByUser(int id);

    TripMember addNewMember(Trip trip);

    String addedTripNotification(Trip trip);

    String addedCommentNotification(Comments comment, int tripId);

    String joinedTripNotification(int id);

    List<Trip> joinedTripsByUser();

    List<Trip> findTripByTripMembersNot();

    List<Trip>findTripByUserByUserId();
}
