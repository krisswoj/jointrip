package pl.jointrip.models;

import javax.persistence.*;
import java.util.*;

@Entity
public class Trip {
    private int id;
    private Date tripCreateDate;
    private Date tripEditDate;
    private Date tripEndDate;
    private String tripFullDesc;
    private Integer tripMembersAmount;
    private Integer tripPriceMember;
    private String tripShortDesc;
    private Integer tripStatus;
    private String tripTitle;
    private User userByUserId;
    private List<TripMember> tripMembers;
    private List<Comments> comments;

    private String tripStreet;
    private String tripCountry;
    private String tripCity;
    private String organizatorPhoneNumber;
    private String organizatorEmail;

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
    @Column(name = "trip_create_date")
    public Date getTripCreateDate() {
        return tripCreateDate;
    }

    public void setTripCreateDate(Date tripCreateDate) {
        this.tripCreateDate = tripCreateDate;
    }

    @Basic
    @Column(name = "trip_edit_date")
    public Date getTripEditDate() {
        return tripEditDate;
    }

    public void setTripEditDate(Date tripEditDate) {
        this.tripEditDate = tripEditDate;
    }

    @Basic
    @Column(name = "trip_end_date")
    public Date getTripEndDate() {
        return tripEndDate;
    }

    public void setTripEndDate(Date tripEndDate) {
        this.tripEndDate = tripEndDate;
    }

    @Basic
    @Column(name = "trip_full_desc")
    public String getTripFullDesc() {
        return tripFullDesc;
    }

    public void setTripFullDesc(String tripFullDesc) {
        this.tripFullDesc = tripFullDesc;
    }

    @Basic
    @Column(name = "trip_members_amount")
    public Integer getTripMembersAmount() {
        return tripMembersAmount;
    }

    public void setTripMembersAmount(Integer tripMembersAmount) {
        this.tripMembersAmount = tripMembersAmount;
    }

    @Basic
    @Column(name = "trip_price_member")
    public Integer getTripPriceMember() {
        return tripPriceMember;
    }

    public void setTripPriceMember(Integer tripPriceMember) {
        this.tripPriceMember = tripPriceMember;
    }

    @Basic
    @Column(name = "trip_short_desc")
    public String getTripShortDesc() {
        return tripShortDesc;
    }

    public void setTripShortDesc(String tripShortDesc) {
        this.tripShortDesc = tripShortDesc;
    }

    @Basic
    @Column(name = "trip_status")
    public Integer getTripStatus() {
        return tripStatus;
    }

    public void setTripStatus(Integer tripStatus) {
        this.tripStatus = tripStatus;
    }

    @Basic
    @Column(name = "trip_title")
    public String getTripTitle() {
        return tripTitle;
    }

    public void setTripTitle(String tripTitle) {
        this.tripTitle = tripTitle;
    }

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

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL)
    public List<TripMember> getTripMembers() {
        return tripMembers;
    }

    public void setTripMembers(List<TripMember> tripMembers) {
        this.tripMembers = tripMembers;
    }

    @OneToMany(mappedBy="trip", cascade = CascadeType.ALL)
    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

    @Basic
    @Column(name = "trip_street")
    public String getTripStreet() {
        return tripStreet;
    }

    public void setTripStreet(String tripStreet) {
        this.tripStreet = tripStreet;
    }

    @Basic
    @Column(name = "trip_country")
    public String getTripCountry() {
        return tripCountry;
    }

    public void setTripCountry(String tripCountry) {
        this.tripCountry = tripCountry;
    }

    @Basic
    @Column(name = "trip_city")
    public String getTripCity() {
        return tripCity;
    }

    public void setTripCity(String tripCity) {
        this.tripCity = tripCity;
    }

    @Basic
    @Column(name = "trip_organizator_phone_number")
    public String getOrganizatorPhoneNumber() {
        return organizatorPhoneNumber;
    }

    public void setOrganizatorPhoneNumber(String organizatorPhoneNumber) {
        this.organizatorPhoneNumber = organizatorPhoneNumber;
    }

    @Basic
    @Column(name = "trip_organizator_email")
    public String getOrganizatorEmail() {
        return organizatorEmail;
    }

    public void setOrganizatorEmail(String organizatorEmail) {
        this.organizatorEmail = organizatorEmail;
    }
}
