package pl.jointrip.services.tripService.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jointrip.dao.TripRepository;
import pl.jointrip.models.entities.trip.Trip;
import pl.jointrip.services.tripService.TripAcceptationService;

@Service
public class TripAcceptationServiceImpl implements TripAcceptationService {


    @Autowired
    TripRepository tripRepository;

    public Trip fetchTripById(Integer id) {
        return tripRepository.findById(id).orElse(null);
    }

    public Iterable<Trip> fetchAllTrips() {
        return tripRepository.findAll();
    }

    public Iterable<Trip> fetchTripsToActivate(Integer status) {
        return tripRepository.findTripByTripStatus(status);
    }

    public boolean changeTripStatus(Integer id, Integer status) {
        try {
            Trip trip = this.fetchTripById(id);
            if (trip != null) {
                trip.setTripStatus(1);
                tripRepository.save(trip);
            } else
                return false;
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
