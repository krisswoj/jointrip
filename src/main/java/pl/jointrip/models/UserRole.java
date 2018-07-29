package pl.jointrip.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_role", schema = "jointrip_home", catalog = "")
@IdClass(UserRolePK.class)
public class UserRole {
    private int userId;
    private int roleId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Id
    @Column(name = "role_id")
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRole userRole = (UserRole) o;
        return userId == userRole.userId &&
                roleId == userRole.roleId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, roleId);
    }
}
