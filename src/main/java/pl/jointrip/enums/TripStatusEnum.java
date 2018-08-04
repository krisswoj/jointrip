package pl.jointrip.enums;

public enum TripStatusEnum {

    WAITING_TRIP(0, "Oczekująca"),
    ACCEPTED_TRIP(1, "Zaakceptowana"),
    REJECTED_TRIP(2, "Odrzucona");

    private Integer status;
    private String description;

    TripStatusEnum(Integer status, String description) {
        this.status = status;
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }
}
