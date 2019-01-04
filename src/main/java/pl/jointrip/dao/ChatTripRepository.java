package pl.jointrip.dao;

import org.springframework.data.repository.CrudRepository;
import pl.jointrip.models.entities.trip.ChatTrip;

public interface ChatTripRepository extends CrudRepository<ChatTrip, Integer> {
}
