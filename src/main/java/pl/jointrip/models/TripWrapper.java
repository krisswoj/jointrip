package pl.jointrip.models;

import java.util.Map;

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

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public Map<String, Integer> getAdminStats() {
        return adminStats;
    }

    public void setAdminStats(Map<String, Integer> adminStats) {
        this.adminStats = adminStats;
    }
}
