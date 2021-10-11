package studentSystem;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        StudentSystemInputParser parser = new StudentSystemInputParser();
        StudentSystem studentSystem = new StudentSystem(new InMemoryStudentRepository());
        while (true) {
            String[] input = scanner.nextLine().split(" ");
            switch (input[0]) {
                case "Exit":
                    return;
                case "Create":
                    Student student = parser.createStudent(input);
                    studentSystem.saveUniqueStudentToRepo(student);
                    break;
                case "Show":
                    String output = studentSystem.studentInformationFor(input[1]);
                    if (output != null) {
                        System.out.println(output);
                    }
                    break;
            }
        }
    }

}
