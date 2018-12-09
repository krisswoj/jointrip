package pl.jointrip.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Entity
public class TripMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    private Trip trip;
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    private User tripMember;
    @Basic
    @Column(name = "status")
    private int status;
    @Basic
    @Column(name = "join_date")
    private Date joinDate;
    @Basic
    @Column(name = "last_edit")
    private Date lastEditDate;

}
