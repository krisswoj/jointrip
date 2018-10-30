package pl.jointrip.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@Entity
public class Comments {
    private int id;
    private Trip trip;
    private String userQuestion;
    private String organisationAnswer;
    private int status;
    private Date addedQuestionDate;
    private Date AnswerDate;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public String getUserQuestion() {
        return userQuestion;
    }

    public void setUserQuestion(String userQuestion) {
        this.userQuestion = userQuestion;
    }

    public String getOrganisationAnswer() {
        return organisationAnswer;
    }

    public void setOrganisationAnswer(String organisationAnswer) {
        this.organisationAnswer = organisationAnswer;
    }

    public Date getAddedQuestionDate() {
        return addedQuestionDate;
    }

    public void setAddedQuestionDate(Date addedQuestionDate) {
        this.addedQuestionDate = addedQuestionDate;
    }

    public Date getAnswerDate() {
        return AnswerDate;
    }

    public void setAnswerDate(Date answerDate) {
        AnswerDate = answerDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
