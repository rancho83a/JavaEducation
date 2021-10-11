package com.example.football.models.dto;

import com.example.football.models.entity.Position;
import com.example.football.models.entity.Stat;
import com.example.football.models.entity.Team;
import com.example.football.models.entity.Town;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.time.LocalDate;

@XmlAccessorType(XmlAccessType.FIELD)
public class PlayerSeedDto {

    @XmlElement(name = "first-name")
    private String firstName;

    @XmlElement(name = "last-name")
    private String lastName;

    @XmlElement(name = "email")
    private String email;

    @XmlElement(name = "birth-date")
    private String birthDate;

    @XmlElement(name = "position")
    private String position;

    @XmlElement(name = "town")
    private TownNameSeedDto town;

    @XmlElement(name = "team")
    private TeamNameSeedDto team;

    @XmlElement(name = "stat")
    private StatIdSeedDto stat;

    public PlayerSeedDto() {
    }

    @NotBlank
    @Size(min = 2)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotBlank
    @Size(min = 2)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotBlank
    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    @NotBlank
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @NotNull
    public TownNameSeedDto getTown() {
        return town;
    }

    public void setTown(TownNameSeedDto town) {
        this.town = town;
    }

    @NotNull
    public TeamNameSeedDto getTeam() {
        return team;
    }

    public void setTeam(TeamNameSeedDto team) {
        this.team = team;
    }

    @NotNull
    public StatIdSeedDto getStat() {
        return stat;
    }

    public void setStat(StatIdSeedDto stat) {
        this.stat = stat;
    }
}
