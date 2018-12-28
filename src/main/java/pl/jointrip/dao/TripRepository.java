package pl.jointrip.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.jointrip.models.entities.trip.Trip;
import pl.jointrip.models.entities.user.User;

import java.util.Collection;
import java.util.List;

@Repository
public interface TripRepository extends CrudRepository<Trip, Integer> {

    Collection<Trip> findTripByTripStatus(Integer status);

    Trip findById(int id);

    @Query("select t from Trip t inner join TripMember as tm on t.id = tm.trip.id where NOT t.tripStatus = 0 and tm.tripMember = :user_id and tm.status = :trip_member_status")
    List<Trip> findTripByTripMembersContains(@Param("user_id") User user, @Param("trip_member_status") int tripMemberStatus);

    @Query("select t from Trip t left join TripMember as tm on t.id = tm.trip.id where NOT t.tripStatus = 0 and t.userByUserId <> :user_id and tm.tripMember is null or tm.tripMember <> :user_id")
    List<Trip> findTripByTripMembersNotContains(@Param("user_id") User user);

    @Query("SELECT t from Trip t WHERE t.userByUserId = :user_id AND t.tripStatus = :trip_status")
    List<Trip> findTripByUserByUserIdAndStatus(@Param("user_id") User user, @Param("trip_status") int tripStatus);

    List<Trip> findTripByUserByUserId(User user);

    @Query("select COUNT (t) from Trip t inner join TripMember as tm on t.id = tm.trip.id where NOT t.tripStatus = 0 and tm.tripMember = :user_id and tm.status = :trip_member_status")
    int findTripByTripMembersContainsAmount(@Param("user_id") User user, @Param("trip_member_status") int tripMemberStatus);

    @Query("select t from Trip t left join TripMember as tm on t.id = tm.trip.id where t = :trip_id and tm.tripMember = :user_id")
    Trip findTripByTripIdAndTripMember(@Param("trip_id") Trip trip, @Param("user_id") User user);

    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false end from Trip t inner join TripMember as tm on t.id = tm.trip.id where t = :trip_id and tm.tripMember = :user_id")
    boolean existsTripByTripMembers(@Param("trip_id") Trip trip, @Param("user_id") User user);

    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false end from Trip t inner join TripMember as tm on t.id = tm.trip.id where t = :trip_id and tm.tripMember = :user_id and tm.status = :statusValue")
    boolean existsTripByTripMembersAndWithStatus(@Param("trip_id") Trip trip, @Param("user_id") User user, @Param("statusValue") int statusValue);

    List<Trip> findTripByTripStatus(int tripStatus);

    List<Trip> findTop3ByTripStatusIsGreaterThanOrderByTripCreateDateDesc(Integer status);

    long countByTripStatus(int status);

    @Query("select count(distinct t.tripCountry) from Trip t")
    long tripCountryCount();

}
