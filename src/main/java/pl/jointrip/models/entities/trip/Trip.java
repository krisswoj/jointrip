package pl.jointrip.models.entities.trip;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import pl.jointrip.models.entities.comments.Comments;
import pl.jointrip.models.entities.documents.Documentstore;
import pl.jointrip.models.entities.documents.ImagesStore;
import pl.jointrip.models.entities.user.User;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@Entity
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @Basic
    @Column(name = "trip_create_date")
    private Date tripCreateDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
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
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User userByUserId;
    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL)
    private List<TripMember> tripMembers;
    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL)
    private List<Comments> comments;
    @OneToMany(mappedBy = "tripId")
    private List<DailyTripPlan> dailyTripPlans;
    @OneToMany(mappedBy = "tripId")
    private List<ImagesStore> imagesStoreList;
    @OneToMany(mappedBy = "tripId")
    private List<TripExtraCosts> tripExtraCosts;
    @OneToMany(mappedBy = "tripId")
    private List<Documentstore> documentstoreList;
    @OneToMany(mappedBy = "tripId")
    private List<ChatTrip> chatTripListByTrip;
}
