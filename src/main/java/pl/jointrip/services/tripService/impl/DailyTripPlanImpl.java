package pl.jointrip.services.tripService.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jointrip.dao.DailyTripPlanRepository;
import pl.jointrip.dao.DocumentsRepository;
import pl.jointrip.dao.TripRepository;
import pl.jointrip.dao.UserRepository;
import pl.jointrip.models.entities.documents.ImagesStore;
import pl.jointrip.models.entities.trip.DailyTripPlan;
import pl.jointrip.models.entities.trip.Trip;
import pl.jointrip.models.entities.trip.TripWrapper;
import pl.jointrip.services.tripService.DailyTripPlanService;
import pl.jointrip.services.userService.UserService;

@Service
public class DailyTripPlanImpl implements DailyTripPlanService {

    private DailyTripPlanRepository dailyTripPlanRepository;
    private TripRepository tripRepository;
    private DocumentsRepository documentsRepository;
    private UserService userService;
    private UserRepository userRepository;

    @Autowired
    public DailyTripPlanImpl(DailyTripPlanRepository dailyTripPlanRepository, TripRepository tripRepository, DocumentsRepository documentsRepository, UserService userService, UserRepository userRepository) {
        this.dailyTripPlanRepository = dailyTripPlanRepository;
        this.tripRepository = tripRepository;
        this.documentsRepository = documentsRepository;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Override
    public TripWrapper tripWithDailyPlan(Trip trip) {
        ImagesStore imagesStore = trip.getImagesStoreList().stream().filter(i -> 1 == i.getMainTripImg()).findAny().orElse(null);
        return new TripWrapper(trip, dailyTripPlanRepository.findAllByTripId(trip), documentsRepository.findAllByTripId(trip), imagesStore, userService.getLoggedUser());
    }

    @Override
    public Trip findTripById(int tripId) {
        return tripRepository.findTripByTripIdAndTripMember(tripRepository.findById(tripId), userService.getLoggedUser());
    }

    @Override
    public Trip findTripByIdAndByUserMember(int tripId, int userId) {
        return tripRepository.findTripByTripIdAndTripMember(tripRepository.findById(tripId), userRepository.findByUserId(userId));
    }

    @Override
    public DailyTripPlan addNewDailyPlan(DailyTripPlan dailyTripPlan, int tripId) {
        dailyTripPlan.setTripId(tripRepository.findById(tripId));
        dailyTripPlan.setStatus(1);
        dailyTripPlan.setAddeddate(null);
        return dailyTripPlanRepository.save(dailyTripPlan);
    }
}
