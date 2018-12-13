package pl.jointrip.models.entities.trip;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Setter
@Getter
@Entity
public class DailyTripPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "title")
    private String title;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "ordinal_number")
    private Integer ordinalnumber;
    @Basic
    @Column(name = "added_date")
    private Timestamp addeddate;
    @Basic
    @Column(name = "status")
    private Integer status;
    @ManyToOne
    @JoinColumn(name = "trip_id", referencedColumnName = "id")
    private Trip tripId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DailyTripPlan that = (DailyTripPlan) o;
        return id == that.id &&
                Objects.equals(tripId, that.tripId) &&
                Objects.equals(title, that.title) &&
                Objects.equals(description, that.description) &&
                Objects.equals(ordinalnumber, that.ordinalnumber) &&
                Objects.equals(addeddate, that.addeddate) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tripId, title, description, ordinalnumber, addeddate, status);
    }
}
