package pl.jointrip.models.entities.documents;

import lombok.Getter;
import lombok.Setter;
import pl.jointrip.models.entities.user.User;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

@Setter
@Getter
@Entity
public class Documentstore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "filename")
    private String filename;
    @Basic
    @Column(name = "file")
    private byte[] file;
    @Basic
    @Column(name = "createdate")
    private Date createdate;
    @Basic
    @Column(name = "modifydate")
    private Date modifydate;
    @Basic
    @Column(name = "filestatus")
    private Integer filestatus;
    @Basic
    @Column(name = "documentkind")
    private Integer documentKind;
    @Basic
    @Column(name = "contentType")
    private String contentType;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User userId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Documentstore that = (Documentstore) o;
        return id == that.id &&
                Arrays.equals(file, that.file) &&
                Objects.equals(createdate, that.createdate) &&
                Objects.equals(modifydate, that.modifydate) &&
                Objects.equals(filestatus, that.filestatus);
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(id, createdate, modifydate, filestatus);
        result = 31 * result + Arrays.hashCode(file);
        return result;
    }

}
