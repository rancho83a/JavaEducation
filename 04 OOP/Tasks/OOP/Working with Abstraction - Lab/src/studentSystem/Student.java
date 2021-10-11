package studentSystem;

public class Student {
    private String name;
    private int age;
    private double grade;

    public Student(String name, int age, double grade) {
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    private String createComentary() {
        if (this.getGrade() >= 5.00) {
            return "Excellent student.";
        }
        if (this.getGrade() < 5.00 && this.getGrade() >= 3.50) {
            return "Average student.";
        }
          return "Very nice person.";
    }

    public    String studentInfo() {
        return String.format("%s is %s years old. %s", this.getName(), this.getAge(),this.createComentary());
    }

    public String getName() {
        return this.name;
    }
    public int getAge() {
        return this.age;
    }
    public double getGrade() {
        return this.grade;
    }

}
