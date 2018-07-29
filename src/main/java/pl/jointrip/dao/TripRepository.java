package pl.jointrip.dao;

import org.springframework.data.repository.CrudRepository;
import pl.jointrip.models.Trip;

import java.util.Collection;

//@Repository
public interface TripRepository extends CrudRepository<Trip, Integer> {
    Collection<Trip> findTripByTripStatus(Integer status);
}
