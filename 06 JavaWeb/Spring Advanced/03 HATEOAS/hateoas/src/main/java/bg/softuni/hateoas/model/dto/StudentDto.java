package bg.softuni.hateoas.model.dto;

public class StudentDto {
    private Long id;
    private String name;
    private int age;

    public Long getId() {
        return id;
    }

    public StudentDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public StudentDto setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public StudentDto setAge(int age) {
        this.age = age;
        return this;
    }
}
