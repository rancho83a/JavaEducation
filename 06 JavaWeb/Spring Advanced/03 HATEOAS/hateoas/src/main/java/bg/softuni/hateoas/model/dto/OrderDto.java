package bg.softuni.hateoas.model.dto;


import org.springframework.hateoas.server.core.Relation;


@Relation(collectionRelation = "orders") // for name "orders" in REST API
public class OrderDto {
    private Long id;
    private Long studentId;
    private Long courseId;

    public Long getId() {
        return id;
    }

    public OrderDto setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getStudentId() {
        return studentId;
    }

    public OrderDto setStudentId(Long studentId) {
        this.studentId = studentId;
        return this;
    }

    public Long getCourseId() {
        return courseId;
    }

    public OrderDto setCourseId(Long courseId) {
        this.courseId = courseId;
        return this;
    }
}
