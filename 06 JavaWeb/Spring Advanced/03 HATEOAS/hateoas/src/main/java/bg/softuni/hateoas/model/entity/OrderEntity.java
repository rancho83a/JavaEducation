package bg.softuni.hateoas.model.entity;


import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="orders")
public class OrderEntity extends BaseEntity{
    @ManyToOne
    private StudentEntity student;

    @ManyToOne
    private CourseEntity course;

    public StudentEntity getStudent() {
        return student;
    }

    public OrderEntity setStudent(StudentEntity student) {
        this.student = student;
        return this;
    }

    public CourseEntity getCourse() {
        return course;
    }

    public OrderEntity setCourse(CourseEntity course) {
        this.course = course;
        return this;
    }
}
