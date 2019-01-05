package pl.jointrip.models.entities.user;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import pl.jointrip.models.entities.documents.Documentstore;
import pl.jointrip.models.entities.documents.ImagesStore;
import pl.jointrip.models.entities.trip.ChatTrip;
import pl.jointrip.models.entities.trip.Trip;
import pl.jointrip.models.entities.trip.TripMember;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "[user]")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;
    @Basic
    @Column(name = "active")
    private Integer active;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "last_name")
    private String lastName;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "password")
    private String password;
    @OneToMany(mappedBy = "userByUserId")
    private Collection<Trip> tripsByUserId;
    @OneToMany(mappedBy = "tripMember", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<TripMember> tripMember;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
    @OneToMany(mappedBy = "userId")
    private Collection<Documentstore> documentstoreCollection;
    @OneToMany(mappedBy = "userId")
    private List<ImagesStore> imagesStoresList;
    @OneToMany(mappedBy = "tripMember")
    private List<ChatTrip> chatTripListMembers;
    @OneToMany(mappedBy = "tripOrganisator")
    private List<ChatTrip> chatTripListOrganisator;
}