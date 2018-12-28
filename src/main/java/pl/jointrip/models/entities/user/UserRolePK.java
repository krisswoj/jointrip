package pl.jointrip.models.entities.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter
public class UserRolePK implements Serializable {
    @Column(name = "user_id")@Id
    private int userId;
    @Column(name = "role_id")@Id
    private int roleId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRolePK that = (UserRolePK) o;
        return userId == that.userId &&
                roleId == that.roleId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, roleId);
    }
}
