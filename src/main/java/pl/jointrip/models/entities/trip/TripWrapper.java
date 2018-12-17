package pl.jointrip.models.entities.trip;

import lombok.Getter;
import lombok.Setter;
import pl.jointrip.models.entities.documents.Documentstore;

import java.util.List;
import java.util.Map;

@Setter
@Getter
public class TripWrapper {

    private Trip trip;
    private Map<String, Integer> statistics;
    private List<DailyTripPlan> dailyTripPlanList;
    private List<Documentstore> documentstoreList;

    public TripWrapper(Trip trip) {
        this.trip = trip;
    }

    public TripWrapper(Trip trip, Map<String, Integer> statistics) {
        this.trip = trip;
        this.statistics = statistics;
    }

    public TripWrapper(Trip trip, List<DailyTripPlan> dailyTripPlanList, List<Documentstore> documentstoreList) {
        this.trip = trip;
        this.dailyTripPlanList = dailyTripPlanList;
        this.documentstoreList = documentstoreList;
    }
}
