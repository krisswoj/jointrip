package pl.jointrip.services.tripService;

import pl.jointrip.models.entities.comments.Comments;
import pl.jointrip.models.entities.comments.CommentsWrapper;
import pl.jointrip.models.entities.trip.Trip;
import pl.jointrip.models.entities.trip.TripMember;
import pl.jointrip.models.entities.trip.TripWrapper;
import pl.jointrip.models.entities.trip.TripsMemberWrapper;
import pl.jointrip.models.entities.user.User;
import pl.jointrip.models.system.SystemNotification;

import java.text.ParseException;
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

    List<TripWrapper> tripsWithStatisicForNoMemberUsers();

    TripWrapper createTripWrapperForNewUsers(Trip trip);

//    TripWrapper createTripWrapperForNewUsers(Trip trip, User user);

    int daysAmountInTrip(Trip trip) throws ParseException;

    void commentsListUpdateByOwner(List<Comments> commentsList);

    void tripMemberListUpdate(List<TripMember> tripMemberList);

    String addedTripNotification(Trip trip);

    String addedCommentNotification(Comments comment, int tripId);

    abstract SystemNotification joinedTripNotification(int id);

    List<TripWrapper> findAllActiveTripsForNoLogUser();

    List<TripWrapper> joinedTripsByUserByTripMemberStatus(int tripMemberStatus);

    List<Trip> findTripByTripMembersNot();

    List<Trip> findTripByUserByUserId();

    List<TripWrapper> tripWithStatisticsForOrganisator();

    TripWrapper createTripWrapperForOrganisator(Trip trip);

    List<Trip> findLatestTrips();

    Trip findById(int id);

    boolean existsTripByTripMembers(Trip trip, User user);

    List<Comments> findByTripAndStatusIs(Trip trip, int status);

    boolean removeTrip(int id);
}
