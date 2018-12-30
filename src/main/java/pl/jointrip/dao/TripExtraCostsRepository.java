package pl.jointrip.dao;

import org.springframework.data.repository.CrudRepository;
import pl.jointrip.models.entities.trip.TripExtraCosts;

public interface TripExtraCostsRepository extends CrudRepository<TripExtraCosts, Integer> {
}
