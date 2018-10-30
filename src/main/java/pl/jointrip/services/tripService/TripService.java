package pl.jointrip.services.tripService;

import pl.jointrip.models.*;

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

    String joinedTripNotification(int id);

    List<Trip> joinedTripsByUser();

    List<Trip> findTripByTripMembersNot();

    List<Trip> findTripByUserByUserId();

    List<TripWrapper> tripWithStatistics();

    TripWrapper createTripWrapper(Trip trip);
}
