package pl.jointrip.models.entities.trip;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import pl.jointrip.models.entities.comments.Comments;
import pl.jointrip.models.entities.documents.Documentstore;
import pl.jointrip.models.entities.user.User;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
@Entity
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "trip_create_date")
    private Date tripCreateDate;
    @Basic
    @Column(name = "trip_edit_date")
    private Date tripEditDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Basic
    @Column(name = "trip_start_date")
    private Date tripStartDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Basic
    @Column(name = "trip_end_date")
    private Date tripEndDate;
    @Basic
    @Column(name = "trip_full_desc")
    private String tripFullDesc;
    @Basic
    @Column(name = "trip_members_amount")
    private Integer tripMembersAmount;
    @Basic
    @Column(name = "trip_price_member")
    private Integer tripPriceMember;
    @Basic
    @Column(name = "trip_short_desc")
    private String tripShortDesc;
    @Basic
    @Column(name = "trip_status")
    private Integer tripStatus;
    @Basic
    @Column(name = "trip_title")
    private String tripTitle;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User userByUserId;
    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL)
    private List<TripMember> tripMembers;
    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL)
    private List<Comments> comments;

    @OneToMany(mappedBy = "tripId")
    private List<DailyTripPlan> dailyTripPlans;

    @Basic
    @Column(name = "trip_street")
    private String tripStreet;
    @Basic
    @Column(name = "trip_country")
    private String tripCountry;
    @Basic
    @Column(name = "trip_city")
    private String tripCity;
    @Basic
    @Column(name = "trip_organizator_phone_number")
    private String organizatorPhoneNumber;
    @Basic
    @Column(name = "trip_organizator_email")
    private String organizatorEmail;
    @OneToMany(mappedBy = "tripId")
    private List<Documentstore> documentstoreList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trip trip = (Trip) o;
        return id == trip.id &&
                Objects.equals(tripCreateDate, trip.tripCreateDate) &&
                Objects.equals(tripEditDate, trip.tripEditDate) &&
                Objects.equals(tripEndDate, trip.tripEndDate) &&
                Objects.equals(tripFullDesc, trip.tripFullDesc) &&
                Objects.equals(tripMembersAmount, trip.tripMembersAmount) &&
                Objects.equals(tripPriceMember, trip.tripPriceMember) &&
                Objects.equals(tripShortDesc, trip.tripShortDesc) &&
                Objects.equals(tripStatus, trip.tripStatus) &&
                Objects.equals(tripTitle, trip.tripTitle);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, tripCreateDate, tripEditDate, tripEndDate, tripFullDesc, tripMembersAmount, tripPriceMember, tripShortDesc, tripStatus, tripTitle);
    }

    public Trip(int id){
          this.id = id;
    }

    public Trip() {
    }
}
