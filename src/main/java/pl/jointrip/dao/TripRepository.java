package pl.jointrip.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pl.jointrip.models.Trip;
import pl.jointrip.models.User;

import java.util.Collection;
import java.util.List;

//@Repository
public interface TripRepository extends CrudRepository<Trip, Integer> {

    Collection<Trip> findTripByTripStatus(Integer status);

    Trip findById(int id);

    List<Trip> findTripByTripMembers(User user);

    List<Trip> findTripByTripMembersNotContains(User user);


}
