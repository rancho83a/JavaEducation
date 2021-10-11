package Person01;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
Scanner scan = new Scanner(System.in);
        String name = scan.nextLine();
        int age = scan.nextInt();

        Child child = new Child(name, age);

        System.out.println(child.getAge());
        System.out.println(child.getName());
    }
}
