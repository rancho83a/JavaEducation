package project.nextleveltechnology.model.Entity;

import javax.persistence.*;

@Entity
@Table(name = "employees")
public class Employee extends BaseEntity {
    private String firstName;
    private String lastName;
    private Integer age;
    private Project project;

    public Employee() {
    }

    @Column(nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(nullable = false)
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @ManyToOne
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}

