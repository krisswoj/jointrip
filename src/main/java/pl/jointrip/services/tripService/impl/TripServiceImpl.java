package pl.jointrip.services.tripService.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.jointrip.dao.*;
import pl.jointrip.models.entities.comments.Comments;
import pl.jointrip.models.entities.comments.CommentsWrapper;
import pl.jointrip.models.entities.documents.ImagesStore;
import pl.jointrip.models.entities.trip.*;
import pl.jointrip.models.entities.user.User;
import pl.jointrip.models.system.SystemNotification;
import pl.jointrip.models.viewModels.tripSearch.TripSearchVM;
import pl.jointrip.services.tripService.TripService;
import pl.jointrip.services.userService.UserService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class TripServiceImpl implements TripService {

    private TripRepository tripRepository;
    private CommentsRepository commentsRepository;
    private UserService userService;
    private TripMemberRepository tripMemberRepository;
    private ChatTripRepository chatTripRepository;

    @PersistenceContext
    private EntityManager em;

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

    @Autowired
    public TripServiceImpl(TripRepository tripRepository, CommentsRepository commentsRepository, UserService userService, TripMemberRepository tripMemberRepository, ChatTripRepository chatTripRepository) {
        this.tripRepository = tripRepository;
        this.commentsRepository = commentsRepository;
        this.userService = userService;
        this.tripMemberRepository = tripMemberRepository;
        this.chatTripRepository = chatTripRepository;
    }

    @Override
    public boolean saveTrip(Trip trip) {

        User loggedUser = userService.getLoggedUser();
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
    public boolean editTrip(Trip trip) {

        trip.setTripEditDate(new Date());
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
    public List<TripWrapper> joinedTripsByUserByTripMemberStatus(int tripMemberStatus) {
        User loggedUser = userService.getLoggedUser();
        List<TripWrapper> tripWrapperList = new ArrayList<>();
        tripRepository.findTripByTripMembersContains(loggedUser, tripMemberStatus).iterator().forEachRemaining(trip -> tripWrapperList.add(createTripWrapperForNewUsers(trip)));
        return tripWrapperList;
    }

    @Override
    public List<TripWrapper> findAllActiveTripsForNoLogUser() {
        List<TripWrapper> tripWrapperList = new ArrayList<>();
        tripRepository.findTripByTripStatus(1).iterator().forEachRemaining(trip -> tripWrapperList.add(createTripWrapperForNewUsers(trip)));
        return tripWrapperList;
    }

    @Override
    public Map<String, List<TripWrapper>> allLoggedUserTrips() {
        Map<String, List<TripWrapper>> listMap = new HashMap<>();
        listMap.put("tripsToVerify", joinedTripsByUserByTripMemberStatus(1));
        listMap.put("waitForPayment", joinedTripsByUserByTripMemberStatus(2));
        listMap.put("paidTrips", joinedTripsByUserByTripMemberStatus(3));
        return listMap;
    }

    @Override
    public Map<String, List<TripWrapper>> tripMapWithStatisticForOrganisator() {
        Map<String, List<TripWrapper>> listMap = new HashMap<>();
        listMap.put("waitForVerify", tripWithStatisticsForOrganisator(0));
        listMap.put("acceptedTrips", tripWithStatisticsForOrganisator(1));
        listMap.put("rejectedTrips", tripWithStatisticsForOrganisator(2));
        listMap.put("finishedTrips", tripWithStatisticsForOrganisator(3));
        return listMap;
    }

    @Override
    public List<TripWrapper> tripWithStatisticsForOrganisator(int tripStatus) {
        User loggedUser = userService.getLoggedUser();
        List<TripWrapper> tripWrapperList = new ArrayList<>();
        tripRepository.findTripByUserByUserIdAndStatus(loggedUser, tripStatus).iterator().forEachRemaining(trip -> tripWrapperList.add(createTripWrapperForOrganisator(trip)));
        return tripWrapperList;
    }

    @Override
    public List<TripWrapper> tripWithStatisticsForOrganisator() {
        User loggedUser = userService.getLoggedUser();
        List<TripWrapper> tripWrapperList = new ArrayList<>();
        tripRepository.findTripByUserByUserId(loggedUser).iterator().forEachRemaining(trip -> tripWrapperList.add(createTripWrapperForOrganisator(trip)));
        return tripWrapperList;
    }

    @Override
    public List<TripWrapper> tripsWithStatisicForNoMemberUsers() {
        List<TripWrapper> tripWrapperList = new ArrayList<>();
        this.findTripByTripMembersNot().iterator().forEachRemaining(trip -> tripWrapperList.add(createTripWrapperForNewUsers(trip)));
        return tripWrapperList;
    }

    @Override
    public TripWrapper createTripWrapperForOrganisator(Trip trip) {
        Map<String, Integer> tripStatistic = new HashMap<>();
        tripStatistic.put("membersToVerify", (int) trip.getTripMembers().stream().filter(m -> m.getStatus() == 1).count());
        tripStatistic.put("waitingForPayment", (int) trip.getTripMembers().stream().filter(m -> m.getStatus() == 2).count());
        tripStatistic.put("paidMembers", (int) trip.getTripMembers().stream().filter(m -> m.getStatus() == 3).count());
        tripStatistic.put("commentsToAnswer", (int) trip.getComments().stream().filter(c -> c.getStatus() == 0).count());
        ImagesStore imagesStore = trip.getImagesStoreList().stream().filter(i -> 1 == i.getMainTripImg()).findAny().orElse(null);
        return new TripWrapper(trip, tripStatistic, imagesStore);
    }

    @Override
    public TripWrapper createTripWrapperForNewUsers(Trip trip) {
        Map<String, Integer> tripStatistic = new HashMap<>();
        tripStatistic.put("daysAmount", daysAmountInTrip(trip));
        tripStatistic.put("membersAmount", trip.getTripMembers().size());
        ImagesStore imagesStore = trip.getImagesStoreList().stream().filter(i -> 1 == i.getMainTripImg()).findAny().orElse(null);
        return new TripWrapper(trip, tripStatistic, imagesStore);
    }

    @Override
    public Map<String, Integer> amountOfTripsForUser() {
        User loggedUser = userService.getLoggedUser();
        Map<String, Integer> tripStatistic = new HashMap<>();
        tripStatistic.put("memberToVerify", tripRepository.findTripByTripMembersContainsAmount(loggedUser, 1));
        tripStatistic.put("memberWaitForPayment", tripRepository.findTripByTripMembersContainsAmount(loggedUser, 2));
        tripStatistic.put("paidMember", tripRepository.findTripByTripMembersContainsAmount(loggedUser, 3));
        return tripStatistic;
    }

    @Override
    public int daysAmountInTrip(Trip trip) {
        LocalDate date1 = trip.getTripEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate date2 = trip.getTripStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return (int) ChronoUnit.DAYS.between(date2, date1);
    }

    @Override
    public ChatTrip chatTripAddMessage(Trip trip, User tripMember, String message, int messageKind) {
        return chatTripRepository.save(new ChatTrip(message, messageKind, trip, tripMember, trip.getUserByUserId()));
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
    public boolean addedTripNotification(Trip trip) {
        return saveTrip(trip);
    }

    @Override
    public SystemNotification addedCommentNotification(Comments comment, int tripId) {
        return (saveCommentByUser(comment, tripId)) ? new SystemNotification("true", commentPositive) : new SystemNotification("fail", commentNegative);
    }

    @Override
    public SystemNotification joinedTripNotification(int id) {
        return (joinToTripByUser(id)) ? new SystemNotification("true", tripJoinPositive) : new SystemNotification("fail", tripJoinNegative);
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
    public List<TripWrapper> findLatestTripsWrapper() {
        List<TripWrapper> tripWrapperList = new ArrayList<>();

        tripRepository
                .findTop3ByTripStatusIsGreaterThanOrderByTripCreateDateDesc(0)
                .iterator()
                .forEachRemaining(
                        trip -> tripWrapperList.add(createTripWrapperForNewUsers(trip)
                        ));

        return tripWrapperList;
    }

    @Override
    public Trip findById(int tripId) {
        return tripRepository.findById(tripId);
    }

    @Override
    public boolean existsTripByTripMembers(Trip trip, User user) {
        return tripRepository.existsTripByTripMembers(trip, user);
    }

    @Override
    public List<Comments> findByTripAndStatusIs(Trip trip, int status) {
        return commentsRepository.findByTripAndStatusIs(trip, status);
    }

    @Override
    public boolean removeTrip(int id) {
        try {
            tripRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<TripWrapper> searchTrips(TripSearchVM tripSearch, boolean logged) {
        List<TripWrapper> tripWrapperList = new ArrayList<>();

        StringBuilder sb = new StringBuilder();

        if (logged) {
            User loggedUser = userService.getLoggedUser();
            sb.append("select * from trip t left join trip_member tm on t.id = tm.trip_id where NOT t.trip_status = 0 and t.user_id <> ")
                    .append(loggedUser.getUserId())
                    .append("and tm.trip_member_user_id is null or tm.trip_member_user_id <> ")
                    .append(loggedUser.getUserId());
        } else {
            sb.append("Select * from trip t where t.trip_status = 1");
        }

        if (!"".equals(tripSearch.getTripTitle())) {
            sb.append(" and LOWER(t.trip_title) like '%").append(tripSearch.getTripTitle().toLowerCase()).append("%'");
        }

        if (!"".equals(tripSearch.getTripCountry())) {
            sb.append(" and LOWER(t.trip_country) like '%").append(tripSearch.getTripCountry().toLowerCase()).append("%'");
        }

        if (!"".equals(tripSearch.getTripCity())) {
            sb.append(" and LOWER(t.trip_city) like '%").append(tripSearch.getTripCity().toLowerCase()).append("%'");
        }

        if (tripSearch.getTripStartDateMin() != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String date = simpleDateFormat.format(tripSearch.getTripStartDateMin());
            sb.append(" and t.trip_start_date >= '").append(date).append("'");
        }

        if (tripSearch.getTripStartDateMax() != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String date = simpleDateFormat.format(tripSearch.getTripStartDateMax());
            sb.append(" and t.trip_start_date <= '").append(date).append("'");
        }

        if (tripSearch.getTripPriceMin() != null) {
            sb.append(" and t.trip_price_member >= ").append(tripSearch.getTripPriceMin());
        }

        if (tripSearch.getTripPriceMax() != null) {
            sb.append(" and t.trip_price_member <= ").append(tripSearch.getTripPriceMax());
        }

        Query q = this.em.createNativeQuery(sb.toString(), Trip.class);
        List<Trip> trips = q.getResultList();

        trips.iterator().forEachRemaining(trip -> tripWrapperList.add(createTripWrapperForNewUsers(trip)));

        return tripWrapperList;
    }
}
