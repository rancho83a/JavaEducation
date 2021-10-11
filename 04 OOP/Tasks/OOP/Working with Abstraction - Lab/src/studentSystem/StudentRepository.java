package studentSystem;

public interface StudentRepository {
    /**
     * Check does Repository have a student with provided name
     * @param name of the student to look for
     * @return true if student is found, false otherwise
     */
    boolean containsStudentWith(String name);

    /**
     * Give a student name, return a student with provided name
     * @param name of the student to find
     * @return the student with the prvided name, NULL -> if  not present
     */
    Student findBy(String name);

    /**
     * Add student to repository
     * @param student to be added
     */
    void save(Student student);
}
