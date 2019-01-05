package pl.jointrip.services.tripService;

import org.springframework.stereotype.Service;
import pl.jointrip.models.entities.trip.DailyTripPlan;
import pl.jointrip.models.entities.trip.Trip;
import pl.jointrip.models.entities.trip.TripWrapper;


public interface DailyTripPlanService {

    TripWrapper tripWithDailyPlan(Trip trip);

    Trip findTripById(int tripId);

    Trip findTripByIdAndByUserMember(int tripId, int userId);

    DailyTripPlan addNewDailyPlan(DailyTripPlan dailyTripPlan, int tripId);
}
