package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "patients")
public class Patient extends BaseEntity {

    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private LocalDate birthday;
    private String picture;
    private Boolean isMedicalInsurance;
    private Set<Visitation> visitations;
    private Set<Visitation> diagnoses;
    public Patient() {
        this.visitations = new HashSet<>();
        this.diagnoses=new HashSet<>();
        this.medicaments=new HashSet<>();
    }

    @OneToMany(mappedBy = "patient")
    public Set<Medicament> getMedicaments() {
        return medicaments;
    }

    public void setMedicaments(Set<Medicament> medicaments) {
        this.medicaments = medicaments;
    }

    private Set<Medicament> medicaments;



@OneToMany(mappedBy = "patient")
    public Set<Visitation> getVisitations() {
        return visitations;
    }

    public void setVisitations(Set<Visitation> visitations) {
        this.visitations = visitations;
    }

    @Column(name = "first_name", nullable = false, length = 30)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", nullable = false, length = 30)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(length = 100)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(nullable = false, length = 50, unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column
    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    //link path may have long name, more than 255
    @Column(columnDefinition = "LONGBLOB")
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }



    public Boolean getMedicalInsurance() {
        return isMedicalInsurance;
    }
    public void setMedicalInsurance(Boolean medicalInsurance) {
        isMedicalInsurance = medicalInsurance;
    }

@OneToMany(mappedBy = "patient")
    public Set<Visitation> getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(Set<Visitation> diagnoses) {
        this.diagnoses = diagnoses;
    }

}
