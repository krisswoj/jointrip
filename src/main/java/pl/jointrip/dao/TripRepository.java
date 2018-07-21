package pl.jointrip.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.jointrip.models.TripEntity;

//@Repository
public interface TripRepository extends CrudRepository<TripEntity, Integer> {
}
