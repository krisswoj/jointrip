package pl.jointrip.dao;

import org.springframework.data.repository.CrudRepository;
import pl.jointrip.models.Trip;

//@Repository
public interface TripRepository extends CrudRepository<Trip, Integer> {
}
