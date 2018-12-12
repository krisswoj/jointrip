package pl.jointrip.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
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
    private Collection<Documentstore> user_id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId &&
                Objects.equals(active, user.active) &&
                Objects.equals(email, user.email) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(name, user.name) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, active, email, lastName, name, password);
    }

}
