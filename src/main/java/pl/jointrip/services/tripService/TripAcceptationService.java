package pl.jointrip.services.tripService;

import pl.jointrip.models.entities.trip.Trip;

public interface TripAcceptationService {
    Iterable<Trip> fetchAllTrips();

    Iterable<Trip> fetchTripsToActivate(Integer status);

    boolean changeTripStatus(Integer id, Integer status);

    Trip fetchTripById(Integer id);
}
