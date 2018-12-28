package pl.jointrip.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.jointrip.models.entities.trip.DailyTripPlan;
import pl.jointrip.models.entities.trip.Trip;

import java.util.List;

@Repository
public interface DailyTripPlanRepository extends CrudRepository<DailyTripPlan, Integer> {

    List<DailyTripPlan> findAllByTripId(Trip trip);

}
