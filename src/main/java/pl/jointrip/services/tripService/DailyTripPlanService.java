package pl.jointrip.services.tripService;

import org.springframework.stereotype.Service;
import pl.jointrip.models.entities.trip.Trip;
import pl.jointrip.models.entities.trip.TripWrapper;


public interface DailyTripPlanService {

    public TripWrapper tripWithDailyPlan(Trip trip);

    Trip findTripById(int tripId);
}
