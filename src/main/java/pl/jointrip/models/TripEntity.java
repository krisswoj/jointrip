package pl.jointrip.models;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Trip", schema = "jointrip_home", catalog = "")
public class TripEntity {
    private int id;
    private Integer userId;
    private String tripTitle;
    private String tripShortDesc;
    private String tripFullDesc;
    private Integer tripMembersAmount;
    private Integer tripPriceMember;
    private int tripStatus;
    private Date tripCreateDate;
    private Date tripEditDate;
    private Date tripEndDate;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "tripTitle")
    public String getTripTitle() {
        return tripTitle;
    }

    public void setTripTitle(String tripTitle) {
        this.tripTitle = tripTitle;
    }

    @Basic
    @Column(name = "tripShortDesc")
    public String getTripShortDesc() {
        return tripShortDesc;
    }

    public void setTripShortDesc(String tripShortDesc) {
        this.tripShortDesc = tripShortDesc;
    }

    @Basic
    @Column(name = "tripFullDesc")
    public String getTripFullDesc() {
        return tripFullDesc;
    }

    public void setTripFullDesc(String tripFullDesc) {
        this.tripFullDesc = tripFullDesc;
    }

    @Basic
    @Column(name = "tripMembersAmount")
    public Integer getTripMembersAmount() {
        return tripMembersAmount;
    }

    public void setTripMembersAmount(Integer tripMembersAmount) {
        this.tripMembersAmount = tripMembersAmount;
    }

    @Basic
    @Column(name = "tripPriceMember")
    public Integer getTripPriceMember() {
        return tripPriceMember;
    }

    public void setTripPriceMember(Integer tripPriceMember) {
        this.tripPriceMember = tripPriceMember;
    }

    @Basic
    @Column(name = "tripStatus")
    public int getTripStatus() {
        return tripStatus;
    }

    public void setTripStatus(int tripStatus) {
        this.tripStatus = tripStatus;
    }

    @Basic
    @Column(name = "tripCreateDate")
    public Date getTripCreateDate() {
        return tripCreateDate;
    }

    public void setTripCreateDate(Date tripCreateDate) {
        this.tripCreateDate = tripCreateDate;
    }

    @Basic
    @Column(name = "tripEditDate")
    public Date getTripEditDate() {
        return tripEditDate;
    }

    public void setTripEditDate(Date tripEditDate) {
        this.tripEditDate = tripEditDate;
    }

    @Basic
    @Column(name = "tripEndDate")
    public Date getTripEndDate() {
        return tripEndDate;
    }

    public void setTripEndDate(Date tripEndDate) {
        this.tripEndDate = tripEndDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TripEntity that = (TripEntity) o;
        return id == that.id &&
                tripStatus == that.tripStatus &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(tripTitle, that.tripTitle) &&
                Objects.equals(tripShortDesc, that.tripShortDesc) &&
                Objects.equals(tripFullDesc, that.tripFullDesc) &&
                Objects.equals(tripMembersAmount, that.tripMembersAmount) &&
                Objects.equals(tripPriceMember, that.tripPriceMember) &&
                Objects.equals(tripCreateDate, that.tripCreateDate) &&
                Objects.equals(tripEditDate, that.tripEditDate) &&
                Objects.equals(tripEndDate, that.tripEndDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userId, tripTitle, tripShortDesc, tripFullDesc, tripMembersAmount, tripPriceMember, tripStatus, tripCreateDate, tripEditDate, tripEndDate);
    }
}
