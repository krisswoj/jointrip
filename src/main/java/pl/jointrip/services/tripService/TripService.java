package pl.jointrip.services.tripService;

import pl.jointrip.models.entities.comments.Comments;
import pl.jointrip.models.entities.comments.CommentsWrapper;
import pl.jointrip.models.entities.trip.Trip;
import pl.jointrip.models.entities.trip.TripMember;
import pl.jointrip.models.entities.trip.TripWrapper;
import pl.jointrip.models.entities.trip.TripsMemberWrapper;
import pl.jointrip.models.entities.user.User;
import pl.jointrip.models.system.SystemNotification;

import java.util.List;

public interface TripService {

    boolean saveTrip(Trip trip);

    boolean joinToTripByUser(int id);

    boolean saveCommentByUser(Comments comment, int tripId);

    TripMember addNewMember(Trip trip);

    TripsMemberWrapper tripsMemberWrapper(int tripId);

    TripMember tripMemberStatusUpdate(TripMember tripMember);

    CommentsWrapper commentsWrapper(int tripId);

    Comments commentUpdateByOwner(Comments comments);

    void commentsListUpdateByOwner(List<Comments> commentsList);

    void tripMemberListUpdate(List<TripMember> tripMemberList);

    String addedTripNotification(Trip trip);

    String addedCommentNotification(Comments comment, int tripId);

    abstract SystemNotification joinedTripNotification(int id);

    List<Trip> joinedTripsByUser();

    List<Trip> findAllActiveTrips();

    List<Trip> findTripByTripMembersNot();

    List<Trip> findTripByUserByUserId();

    List<TripWrapper> tripWithStatistics();

    TripWrapper createTripWrapper(Trip trip);

    List<Trip> findLatestTrips();

    Trip findById(int id);

    boolean existsTripByTripMembers(Trip trip, User user);

    List<Comments> findByTripAndStatusIs(Trip trip, int status);
}
