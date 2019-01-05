package pl.jointrip.models.entities.trip;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import pl.jointrip.models.entities.user.User;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Entity
public class ChatTrip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "message_chat")
    private String message;

    @Basic
    @Column(name = "message_kind")
    private int messageKind;

    @Basic
    @Column(name = "sent_date")
    private Date sentDate;

    @Basic
    @Column(name = "read_date")
    private Date readDate;

    @ManyToOne
    @JoinColumn(name = "trip_id", referencedColumnName = "id")
    private Trip tripId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    private User tripMember;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    private User tripOrganisator;

    public ChatTrip(String message, int messageKind, Trip tripId, User tripMember, User tripOrganisator) {
        this.message = message;
        this.messageKind = messageKind;
        this.tripId = tripId;
        this.tripMember = tripMember;
        this.tripOrganisator = tripOrganisator;
    }

    public ChatTrip() {
    }
}
