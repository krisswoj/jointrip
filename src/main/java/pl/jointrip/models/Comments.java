package pl.jointrip.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Entity
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    private Trip trip;
    private String userQuestion;
    private String organisationAnswer;
    private int status;
    private Date addedQuestionDate;
    private Date AnswerDate;

    public Date getAnswerDate() {
        return AnswerDate;
    }

    public void setAnswerDate(Date answerDate) {
        AnswerDate = answerDate;
    }

}
