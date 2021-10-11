package task05FootballTeamGenerator;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String name;
    private List<Player> players;

    public Team(String name) {
        this.setName(name);
        this.players = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        if (null == name || name.trim().isEmpty()) {
            throw new IllegalArgumentException("A name should not be empty.");
        }
        this.name = name.trim();
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public void removePlayer(String playerName) {
        if (!this.players.removeIf(p -> p.getName().equals(playerName))) {
            throw new IllegalArgumentException("Player " + playerName + " is not in " + this.getName() + " team.");
        }
    }

    public double getRating() {
        return this.players.stream().mapToDouble(Player::overallSkillLevel).sum();
    }
}
