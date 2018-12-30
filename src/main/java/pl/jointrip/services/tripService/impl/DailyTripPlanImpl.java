package pl.jointrip.services.tripService.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jointrip.dao.DailyTripPlanRepository;
import pl.jointrip.dao.DocumentsRepository;
import pl.jointrip.dao.TripRepository;
import pl.jointrip.models.entities.documents.ImagesStore;
import pl.jointrip.models.entities.trip.DailyTripPlan;
import pl.jointrip.models.entities.trip.Trip;
import pl.jointrip.models.entities.trip.TripWrapper;
import pl.jointrip.services.tripService.DailyTripPlanService;
import pl.jointrip.services.userService.UserService;

import java.util.Map;

@Service
public class DailyTripPlanImpl implements DailyTripPlanService {


    @Autowired
    DailyTripPlanRepository dailyTripPlanRepository;

    @Autowired
    TripRepository tripRepository;

    @Autowired
    DocumentsRepository documentsRepository;

    @Autowired
    UserService userService;

    @Override
    public TripWrapper tripWithDailyPlan(Trip trip) {
        ImagesStore imagesStore = trip.getImagesStoreList().stream().filter(i -> 1 == i.getMainTripImg()).findAny().orElse(null);
        return new TripWrapper(trip, dailyTripPlanRepository.findAllByTripId(trip), documentsRepository.findAllByTripId(trip), imagesStore);
    }

    @Override
    public Trip findTripById(int tripId) {
        return tripRepository.findTripByTripIdAndTripMember(tripRepository.findById(tripId), userService.getLoggedUser());
    }

    @Override
    public DailyTripPlan addNewDailyPlan(DailyTripPlan dailyTripPlan, int tripId) {
        dailyTripPlan.setTripId(tripRepository.findById(tripId));
        dailyTripPlan.setStatus(1);
        dailyTripPlan.setAddeddate(null);
        return dailyTripPlanRepository.save(dailyTripPlan);
    }

//    public Map<String, String> tripExtraCosts()
}
