package pl.jointrip.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "[user]")
public class User {
    private int userId;
    private Integer active;
    private String email;
    private String lastName;
    private String name;
    private String password;
    private Collection<Trip> tripsByUserId;
    private List<TripMember> tripMember;
    private Set<Role> roles;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "active")
    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

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

    @OneToMany(mappedBy = "userByUserId")
    public Collection<Trip> getTripsByUserId() {
        return tripsByUserId;
    }

    public void setTripsByUserId(Collection<Trip> tripsByUserId) {
        this.tripsByUserId = tripsByUserId;
    }

    @OneToMany(mappedBy = "tripMember", cascade = CascadeType.ALL)
    @JsonManagedReference
    public List<TripMember> getTripMember() {
        return tripMember;
    }

    public void setTripMember(List<TripMember> tripMember) {
        this.tripMember = tripMember;
    }
}
