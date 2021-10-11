package project.nextleveltechnology.model.dto;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@XmlRootElement(name="project")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProjectSeedDto {
    @XmlElement(name = "name")
    private String name;

    @XmlElement(name="description")
    private String description;

    @XmlElement(name="start-date")
    private Date startDate;

    @XmlElement(name="is-finished")
    private Boolean isFinished;

    @XmlElement(name="payment")
    private BigDecimal payment;

    @XmlElement(name="company")
    private CompanySeedDto company;

    public ProjectSeedDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Boolean getFinished() {
        return isFinished;
    }

    public void setFinished(Boolean finished) {
        isFinished = finished;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public CompanySeedDto getCompany() {
        return company;
    }

    public void setCompany(CompanySeedDto company) {
        this.company = company;
    }
}
