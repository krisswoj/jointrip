package pl.jointrip.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.jointrip.models.Trip;
import pl.jointrip.models.User;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Repository
public interface TripRepository extends CrudRepository<Trip, Integer>{

    Collection<Trip> findTripByTripStatus(Integer status);

    Trip findById(int id);

    @Query("select t from Trip t inner join TripMember as tm on t.id = tm.id where NOT t.tripStatus = 0 and tm.tripMember = :user_id")
    List<Trip> findTripByTripMembersContains(@Param("user_id") User user);

    @Query("select t from Trip t left join TripMember as tm on t.id = tm.id where NOT t.tripStatus = 0 and tm.tripMember is null or tm.tripMember <> :user_id")
    List<Trip> findTripByTripMembersNotContains(@Param("user_id") User user);

    List<Trip>findTripByUserByUserId(User user);

    List<Trip> findTop3ByTripCreateDateBeforeOrderByTripCreateDateDesc(Date date);

}
