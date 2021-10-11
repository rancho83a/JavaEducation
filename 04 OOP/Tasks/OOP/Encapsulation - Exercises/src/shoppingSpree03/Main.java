package shoppingSpree03;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Map<String, Team> teamMap = new HashMap<>();
        String inputLine;


        while (!"END".equals(inputLine = scan.nextLine())) {
            String[] tokens = inputLine.split(";");
            String teamName = tokens[1];

            switch (tokens[0]) {
                case "Team":
                    try {
                        Team team = new Team(teamName);
                        teamMap.put(teamName, team);
                    } catch (IllegalArgumentException iae) {
                        System.out.println(iae.getMessage());
                    }
                    break;

                case "Add":
                    if (!isTeamExist(teamMap, teamName)) break;
                    String playerName = tokens[2];
                    int endurance = Integer.parseInt(tokens[3]);
                    int sprint = Integer.parseInt(tokens[4]);
                    int dribble = Integer.parseInt(tokens[5]);
                    int passing = Integer.parseInt(tokens[6]);
                    int shooting = Integer.parseInt(tokens[7]);
                    try {
                        Player player = new Player(playerName, endurance, sprint, dribble, passing, shooting);
                        teamMap.get(teamName).addPlayer(player);
                    } catch (IllegalArgumentException iae) {
                        System.out.println(iae.getMessage());
                    }
                    break;
                case "Remove":
                    if (isTeamExist(teamMap, teamName)) {
                        String playerNameToRemove = tokens[2];
                        try {
                            teamMap.get(teamName).removePlayer(playerNameToRemove);
                        } catch (IllegalArgumentException iae) {
                            System.out.println(iae.getMessage());
                        }
                    }
                    break;
                case "Rating":
                    if (isTeamExist(teamMap, teamName)) {
                        int rating = (int) Math.round(teamMap.get(teamName).getRating());
                        System.out.println(teamName + " - " + rating);
                    }
                    break;
            }
        }
    }

    private static boolean isTeamExist(Map<String, Team> teamMap, String teamName) {
        if (!teamMap.containsKey(teamName)) {
            System.out.println("Team " + teamName + " does not exist.");
            return false;
        }
        return true;
    }
}
