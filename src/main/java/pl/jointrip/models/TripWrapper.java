package pl.jointrip.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
public class TripWrapper {

    private Trip trip;
    private Map<String, Integer> adminStats;

    public TripWrapper(Trip trip) {
        this.trip = trip;
    }

    public TripWrapper(Trip trip, Map<String, Integer> adminStats) {
        this.trip = trip;
        this.adminStats = adminStats;
    }



}
