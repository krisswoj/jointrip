package pl.jointrip.models.entities.documents;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import pl.jointrip.models.entities.trip.Trip;
import pl.jointrip.models.entities.user.User;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Entity
public class ImagesStore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "img_name")
    private String imgName;

    @Basic
    @Column(name = "img_description")
    private String imgDescription;

    @Basic
    @Column(name = "main_trip_img")
    private int mainTripImg;

    @Basic
    @Column(name = "create_date")
    private Date createDate;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User userId;

    @ManyToOne
    @JoinColumn(name = "trip_id", referencedColumnName = "id")
    private Trip tripId;

    public ImagesStore(String imgName, User userId, Trip tripId) {
        this.imgName = imgName;
        this.userId = userId;
        this.tripId = tripId;
    }

    public ImagesStore() {
    }
}
