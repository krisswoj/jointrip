package pl.jointrip.models.entities.trip;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
public class TripExtraCosts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "title")
    private String title;
    @Basic
    @Column(name = "price")
    private Double price;
    @Basic
    @Column(name = "currency")
    private String currency;
    @ManyToOne
    @JoinColumn(name = "trip_id", referencedColumnName = "id")
    private Trip tripId;
}
