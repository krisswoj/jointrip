package pl.jointrip.models;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Comments {
    private int id;
    private String text;
    private int userId;
    private int tripId;
    private User user;
    private Trip trip;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Basic
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "trip_id")
    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    @ManyToOne
    @JoinColumn(name="trip_id", nullable=false, insertable = false, updatable = false)
    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false, insertable = false, updatable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comments comments = (Comments) o;
        return id == comments.id &&
                userId == comments.userId &&
                tripId == comments.tripId &&
                Objects.equals(text, comments.text);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, text, userId, tripId);
    }
}
