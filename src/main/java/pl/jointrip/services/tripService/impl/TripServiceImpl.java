package pl.jointrip.services.tripService.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.jointrip.dao.CommentsRepository;
import pl.jointrip.dao.TripMemberRepository;
import pl.jointrip.dao.TripRepository;
import pl.jointrip.dao.UserRepository;
import pl.jointrip.models.entities.comments.Comments;
import pl.jointrip.models.entities.comments.CommentsWrapper;
import pl.jointrip.models.entities.trip.Trip;
import pl.jointrip.models.entities.trip.TripMember;
import pl.jointrip.models.entities.trip.TripWrapper;
import pl.jointrip.models.entities.trip.TripsMemberWrapper;
import pl.jointrip.models.entities.user.User;
import pl.jointrip.models.system.SystemNotification;
import pl.jointrip.services.tripService.TripService;
import pl.jointrip.services.userService.UserService;

import java.util.*;

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
    public boolean joinToTripByUser(int id) {

        User loggedUser = userService.getLoggedUser();
        Trip trip = tripRepository.findById(id);

        if (trip.getUserByUserId().getUserId() == loggedUser.getUserId())
            return false;

        if (tripRepository.existsTripByTripMembers(trip, loggedUser))
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
    public boolean saveCommentByUser(Comments comment, int tripId) {
        comment.setStatus(0);
        comment.setAddedQuestionDate(new Date());
        comment.setTrip(tripRepository.findById(tripId));

        try {
            commentsRepository.save(comment);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public TripMember addNewMember(Trip trip) {
        User loggedUser = userService.getLoggedUser();
        TripMember tripMember = new TripMember();
        tripMember.setTripMember(loggedUser);
        tripMember.setStatus(1);
        tripMember.setTrip(trip);
        return tripMemberRepository.save(tripMember);
    }

    @Override
    public TripMember tripMemberStatusUpdate(TripMember tripMember) {
        TripMember tm = tripMemberRepository.findById(tripMember.getId());
        tm.setStatus(tripMember.getStatus());
        return tripMemberRepository.save(tm);
    }

    @Override
    public CommentsWrapper commentsWrapper(int tripId) {
        List<Comments> commentsList = new ArrayList<>();
        tripRepository.findById(tripId).getComments().iterator().forEachRemaining(commentsList::add);
        commentsList.sort(Comparator.comparing(Comments::getId));
        return new CommentsWrapper(commentsList);
    }

    @Override
    public Comments commentUpdateByOwner(Comments comments) {
        Comments ct = commentsRepository.findById(comments.getId());
        ct.setStatus(1);
        if (comments.getOrganisationAnswer() != null) {
            ct.setOrganisationAnswer(comments.getOrganisationAnswer());
            ct.setAnswerDate(new Date());
        }
        return commentsRepository.save(ct);
    }

    @Override
    public TripsMemberWrapper tripsMemberWrapper(int tripId) {
        List<TripMember> tripMembers = new ArrayList<>();
        tripRepository.findById(tripId).getTripMembers().iterator().forEachRemaining(tripMembers::add);
        tripMembers.sort(Comparator.comparing(TripMember::getId));
        return new TripsMemberWrapper(tripMembers);
    }

    @Override
    public List<TripWrapper> tripWithStatistics() {
        User loggedUser = userService.getLoggedUser();
        List<TripWrapper> tripWrapperList = new ArrayList<>();
        tripRepository.findTripByUserByUserId(loggedUser).iterator().forEachRemaining(trip -> tripWrapperList.add(createTripWrapper(trip)));
        return tripWrapperList;
    }

    @Override
    public TripWrapper createTripWrapper(Trip trip) {
        Map<String, Integer> tripStatistic = new HashMap<>();
        tripStatistic.put("membersToVerify", (int) trip.getTripMembers().stream().filter(m -> m.getStatus() == 1).count());
        tripStatistic.put("waitingForPayment", (int) trip.getTripMembers().stream().filter(m -> m.getStatus() == 2).count());
        tripStatistic.put("paidMembers", (int) trip.getTripMembers().stream().filter(m -> m.getStatus() == 3).count());
        tripStatistic.put("commentsToAnswer", (int) trip.getComments().stream().filter(c -> c.getStatus() == 0).count());
        return new TripWrapper(trip, tripStatistic);
    }

    @Override
    public void commentsListUpdateByOwner(List<Comments> commentsList) {
        commentsList.forEach(this::commentUpdateByOwner);
    }

    @Override
    public void tripMemberListUpdate(List<TripMember> tripMemberList) {
        tripMemberList.forEach(this::tripMemberStatusUpdate);
    }

    @Override
    public String addedTripNotification(Trip trip) {
        return (saveTrip(trip)) ? tripPositive : tripNegative;
    }

    @Override
    public String addedCommentNotification(Comments comment, int tripId) {
        return (saveCommentByUser(comment, tripId)) ? commentPositive : commentNegative;
    }

    @Override
    public SystemNotification joinedTripNotification(int id) {
        return (joinToTripByUser(id)) ? new SystemNotification("true", tripJoinPositive) : new SystemNotification("fail", tripJoinNegative);
    }

    @Override
    public List<Trip> joinedTripsByUser() {
        User loggedUser = userService.getLoggedUser();
        return tripRepository.findTripByTripMembersContains(loggedUser);
    }

    @Override
    public List<Trip> findAllActiveTrips(){
        return tripRepository.findTripByTripStatus(1);
    }

    @Override
    public List<Trip> findTripByTripMembersNot() {
        User loggedUser = userService.getLoggedUser();
        return tripRepository.findTripByTripMembersNotContains(loggedUser);
    }

    @Override
    public List<Trip> findTripByUserByUserId() {
        User loggedUser = userService.getLoggedUser();
        return tripRepository.findTripByUserByUserId(loggedUser);
    }

    @Override
    public List<Trip> findLatestTrips() {
        List<Trip> trips = tripRepository.findTop3ByTripStatusIsGreaterThanOrderByTripCreateDateDesc(0);
        return trips;
    }
    @Override
    public Trip findById(int tripId){
        return tripRepository.findById(tripId);
    }
    @Override
    public boolean existsTripByTripMembers(Trip trip, User user){
        return tripRepository.existsTripByTripMembers(trip, user);
    }
    @Override
    public List<Comments> findByTripAndStatusIs(Trip trip, int status){
        return commentsRepository.findByTripAndStatusIs(trip, status);
    }

    @Override
    public boolean removeTrip(int id){
        try{
            tripRepository.deleteById(id);
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
