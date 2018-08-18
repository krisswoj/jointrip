package pl.jointrip.services.tripService;

import org.springframework.stereotype.Service;
import pl.jointrip.models.Trip;

public interface TripService {

   boolean saveTrip(Trip trip);
}
