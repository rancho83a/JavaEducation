package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name="visitations")
public class Visitation extends BaseEntity{
    private LocalDate date;
    private String comment;
    private Patient patient;

    public Visitation() {
    }

    @ManyToOne
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }



    @Column(nullable = false)
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
@Column(columnDefinition = "TEXT", nullable = false)
    public String getComment() {
        return comment;
    }

    public void setComment(String comments) {
        this.comment = comments;
    }
}
