import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/";
    private static final String DATABASE_NAME = "minions_db";
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Connection connection;

    public static void main(String[] args) throws SQLException, IOException {
        connection = getConnection();

        System.out.println("Enter the number of task:");
        int number = Integer.parseInt(reader.readLine());

        switch (number) {
            case 2 -> ex2();
            case 3 -> ex3();
            case 5 -> ex5();
            case 4 -> ex4();
            case 6 -> ex6();
            case 7 -> ex7();
            case 8 -> ex8();
            case 9 -> ex9();

            default -> System.out.println("Please enter correct number of task");
        }
    }

    private static void ex2() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT v.name, COUNT(DISTINCT mv.minion_id) as count\n" +
                "FROM villains as v\n" +
                "JOIN minions_villains mv on v.id = mv.villain_id\n" +
                "GROUP BY v.id\n" +
                "HAVING count> ?\n" +
                "ORDER BY count DESC;");

        preparedStatement.setInt(1, 15);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            System.out.println(String.format("%s %d", resultSet.getString(1), resultSet.getInt(2)));
        }
    }

    private static void ex3() throws IOException {

        System.out.println("Enter villain id:");
        int id = Integer.parseInt(reader.readLine());


        String villain_name = null;
        try {
            villain_name = findEntityNameById("villains", id);

            System.out.printf("Villain: %s%n", villain_name);

            getAllMinionsByVillianId(id).forEach(System.out::println);
        } catch (SQLException throwables) {
            System.out.println("No villain with ID " + id + " exists in the database.");
        }

    }

    private static void ex4() throws IOException, SQLException {
        System.out.println("Enter on the first line minions info separated by space via placeholder: <Minion: name age town>" + System.lineSeparator() +
                "Enter on the second line villains info separated by space via placeholder: <Villain: name>");
        String[] tokens = reader.readLine().split("\\s+");
        String minionName = tokens[1];
        int age = Integer.parseInt(tokens[2]);
        String town = tokens[3];
        boolean isNewMinion = false;


        int townId = getEntityIdByName(town, "towns");
        if (townId < 0) {
            insertEntityInTown(town);
            System.out.println("Town " + town + " was added to the database.");
            townId = getEntityIdByName(town, "towns");
        }

        int minionId = getEntityIdByName(minionName, "minions");
        if (minionId < 0) {
            insertEntityInMinions(minionName, age, townId);
            minionId = getEntityIdByName(minionName, "minions");
            isNewMinion = true;
        }

        tokens = reader.readLine().split("\\s+");
        String villainName = tokens[1];


        int villainId = getEntityIdByName(villainName, "villains");
        if (villainId < 0) {
            insertEntityInVillains(villainName);
            System.out.println("Villain " + villainName + " was added to the database.");
            villainId = getEntityIdByName(villainName, "villains");
        }
        if (isNewMinion) {
            insertEntityInMinions_villains(minionId, villainId);
            System.out.println(String.format("Successfully added %s to be minion of %s", minionName, villainName));

        }
    }

    private static void ex5() throws IOException, SQLException {
        System.out.println("Enter country:");
        String country = reader.readLine();
        int count = changeTownNamesAndGetCountByCountry(country);
        if (count == 0) {
            System.out.println("No town names were affected.");
            return;
        }
        System.out.println(count + " town names were affected.");

        System.out.println(getChangedTowns(country));
    }

    private static void ex6() throws IOException {
        System.out.println("Enter villain id:");
        int id = Integer.parseInt(reader.readLine());

        try {
            int affectedEntities = deleteMinionsByVillainID(id);

            String villain_name = findEntityNameById("villains", id);

            deleteVillainById(id);

            System.out.printf("%s was deleted%n" +
                    "%d minions released", villain_name, affectedEntities);

        } catch (SQLException throwables) {
            System.out.println("No such villain was found");
        }

    }

    private static void ex7() throws SQLException {
        String query = "SELECT name FROM minions";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<String> res = new ArrayList<>();

        while (resultSet.next()) {
            res.add(resultSet.getString(1));
        }
        int startIndex = 0;
        int endIndex = res.size() - 1;
        for (int i = 0; i < res.size(); i++) {

            System.out.println(i % 2 == 0 ?
                    res.get(startIndex++) : res.get(endIndex--));
        }
    }

    private static void ex8() throws IOException {
        System.out.println("Enter minion IDs, separated by space");
        List<Integer> ids = Arrays.stream(reader.readLine().split("\\s+")).map(Integer::parseInt).collect(Collectors.toList());

        try {
            for (Integer id : ids) {
                String query = "UPDATE minions\n" +
                        "        SET age=age+1, name=LOWER(name)\n" +
                        "    WHERE id=?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();

                System.out.println(getAllMinionsById(id));
            }
        } catch (SQLException ex) {
            System.out.println("Incorrect id. Enter correct IDs of minions.");
        }
    }

    private static void ex9() throws IOException, SQLException {
        System.out.println("Enter minion id");
        int id = Integer.parseInt(reader.readLine());

        CallableStatement callableStatement = connection.prepareCall("CALL usp_get_older(?)");
        callableStatement.setInt(1, id);
        callableStatement.executeUpdate();

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT name, age FROM minions WHERE id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        System.out.printf("%s %d%n", resultSet.getString(1), resultSet.getInt(2));

    }


    private static Set<String> getAllMinionsByVillianId(int id) throws SQLException {

        Set<String> res = new LinkedHashSet<>();

        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT DISTINCT m.name, m.age FROM minions as m " +
                        "JOIN minions_villains mv on m.id = mv.minion_id   " +
                        "              WHERE mv.villain_id=?");

        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        int count = 0;
        while (resultSet.next()) {
            res.add((String.format("%d. %s %d", ++count, resultSet.getString(1), resultSet.getInt(2))));
        }
        return res;
    }

    private static String findEntityNameById(String tableName, int id) throws SQLException {
        String query = String.format("SELECT name FROM %s WHERE id=?", tableName);
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getString(1);
    }

    private static Set<String> getChangedTowns(String country) throws SQLException {
        Set<String> res = new LinkedHashSet<>();

        String query = "SELECT name from towns\n" +
                "WHERE country=?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, country);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            res.add(resultSet.getString(1));
        }
        return res;
    }

    private static int changeTownNamesAndGetCountByCountry(String country) throws SQLException {
        String query = "UPDATE towns\n" +
                "SET name=UPPER(name)\n" +
                "WHERE country=?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, country);
        int affectedRow = preparedStatement.executeUpdate();

        return affectedRow;

    }

    private static String getAllMinionsById(int id) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT DISTINCT m.name, m.age FROM minions as m WHERE id=?");

        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();
        return String.format("%s %d", resultSet.getString(1), resultSet.getInt(2));
    }

    private static void insertEntityInMinions_villains(int minionId, int villainId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO minions_villains(minion_id, villain_id) VALUES (?,?)");
        preparedStatement.setInt(1, minionId);
        preparedStatement.setInt(2, villainId);
        preparedStatement.executeUpdate();
    }

    private static void insertEntityInVillains(String villainName) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO villains(name,evilness_factor) VALUES (?,'evil')");
        preparedStatement.setString(1, villainName);
        preparedStatement.executeUpdate();
    }

    private static void insertEntityInMinions(String minionName, int age, int townId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO minions(name,age,town_id) VALUES (?,?,?)");
        preparedStatement.setString(1, minionName);
        preparedStatement.setInt(2, age);
        preparedStatement.setInt(3, townId);
        preparedStatement.executeUpdate();

    }

    private static void insertEntityInTown(String town) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO towns(name) VALUES (?)");
        preparedStatement.setString(1, town);
        preparedStatement.executeUpdate();

    }

    private static int getEntityIdByName(String entityName, String tableName) throws SQLException {
        String query = "SELECT id FROM " + tableName + " WHERE name =?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, entityName);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet.next() ? resultSet.getInt(1) : -1;
    }

    private static void deleteVillainById(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE from villains WHERE id=?");
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }

    private static int deleteMinionsByVillainID(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE from minions_villains WHERE villain_id=?");
        preparedStatement.setInt(1, id);
        return preparedStatement.executeUpdate();
    }

    private static Connection getConnection() throws SQLException, IOException {
        Properties properties = new Properties();

        System.out.println("Enter user:");
        String user = reader.readLine();
        System.out.println("Enter password:");
        String password = reader.readLine();

        properties.setProperty("user", user);
        properties.setProperty("password", password);


//        properties.setProperty("user", "root");
//        properties.setProperty("password", "mypass");


        return DriverManager.getConnection(CONNECTION_STRING + DATABASE_NAME, properties);
    }
}
