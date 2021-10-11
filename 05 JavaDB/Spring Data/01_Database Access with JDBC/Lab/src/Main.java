import java.sql.*;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/soft_uni", "root", "mypass");
        Statement statement = connection.createStatement();

//        listProject(connection);
//
        Scanner scan = new Scanner(System.in);
        double salary = Double.parseDouble(scan.nextLine());

        Statement infoSchema = connection.createStatement();
        ResultSet rs = infoSchema.executeQuery("SELECT * FROM information_schema.COLUMNS WHERE TABLE_NAME ='employees' AND TABLE_SCHEMA='soft_uni'");


        ResultSet resultSet = statement.executeQuery("SELECT * FROM employees WHERE salary > " + salary);


        while (resultSet.next()) {
            while (rs.next()) {
                String column_name = rs.getString("COLUMN_NAME");
                System.out.print(column_name + ": " + resultSet.getString(column_name) + " | ");
            }
            System.out.println();


//            String jobTitle = resultSet.getString(5);
//            long id = resultSet.getLong(1);
//            System.out.println(id + " | " + jobTitle);
        }

        System.out.println("----------------------------------");

        String alter_query = "alter table projects change name full_name varchar(50) not null";

        Statement alterStm = connection.createStatement();
        alterStm.execute(alter_query);


    }

    static void listProject(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM projects");
        while (resultSet.next()) {
            System.out.println(resultSet.getString("name"));
        }
//        connection.close();
    }
}
