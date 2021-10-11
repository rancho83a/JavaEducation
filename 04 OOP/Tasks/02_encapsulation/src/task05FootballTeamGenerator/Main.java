package task05FootballTeamGenerator;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Map<String, Team> teams = new HashMap<>();

        String input = "";

        while (!"END".equals((input = scan.nextLine()))) {
            String[] tokens = input.split(";");
            String teamName = tokens[1];
            switch (tokens[0]) {
                case "Team":
                    teams.putIfAbsent(teamName, new Team(teamName));
                    break;

                case "Add":
                    if (!isTeamExist(teams, teamName)) break;
                    try {
                        Player player = new Player(tokens[2], Integer.parseInt(tokens[3]),
                                Integer.parseInt(tokens[4]), Integer.parseInt(tokens[5]),
                                Integer.parseInt(tokens[6]), Integer.parseInt(tokens[7]));
                        teams.get(teamName).addPlayer(player);
                    } catch (IllegalArgumentException ex) {
                        System.out.println(ex.getMessage());
                    }

                    break;
                case "Remove":
                    if (!isTeamExist(teams, teamName)) break;
                    try {
                        teams.get(teamName).removePlayer(tokens[2]);
                    } catch (IllegalArgumentException ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case "Rating":
                    if (!isTeamExist(teams, teamName)) break;
                    System.out.println(teamName + " - " + (int)Math.round(teams.get(teamName).getRating()));

                    break;
            }
        }

    }

    private static boolean isTeamExist(Map<String, Team> teams, String teamName) {
        if (!teams.containsKey(teamName)) {
            System.out.println(String.format("Team %s does not exist.", teamName));
            return false;
        }
        return true;
    }
}
