package studentSystem;

public class StudentSystem {

    private StudentRepository repo;

    public StudentSystem(StudentRepository repo) {
        this.repo = repo;
    }

    public String studentInformationFor(String name) {
        if (repo.containsStudentWith(name)) {
            Student student = repo.findBy(name);
            return student.studentInfo();
        }
        return null;
    }

    public void saveUniqueStudentToRepo(Student student) {
        if (!repo.containsStudentWith(student.getName())) {
            repo.save(student);
        }
    }
}
