package studentSystem;

import studentSystem.Student;
import studentSystem.StudentRepository;

import java.util.HashMap;

public class InMemoryStudentRepository implements StudentRepository {
    private final HashMap<String, Student> map;

    public InMemoryStudentRepository(){
        this.map = new HashMap<>();
    }

    @Override
    public boolean containsStudentWith(String name) {
        return this.map.containsKey(name);
    }

    @Override
    public Student findBy(String name) {
        return this.map.get(name);
    }

    @Override
    public void save(Student student) {
        map.put(student.getName(),student);

    }
}
