package pl.jointrip.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pl.jointrip.models.Trip;
import pl.jointrip.models.User;

import java.util.Collection;

//@Repository
public interface TripRepository extends CrudRepository<Trip, Integer> {
    Collection<Trip> findTripByTripStatus(Integer status);
    Trip findById(int id);

    //    Collection<Trip> findTripByUserByUserId(User user);
//    Trip findTripById(Integer id);
//
//
//    @Query("SELECT p FROM Person p WHERE LOWER(p.lastName) = LOWER(:lastName)")
//    Collection<Trip> findTripByUserByUserId2(@Param("user_id") Integer user_id);



}
