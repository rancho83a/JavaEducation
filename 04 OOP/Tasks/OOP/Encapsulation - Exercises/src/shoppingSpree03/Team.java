package shoppingSpree03;

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
        return this.name;
    }

    private void setName(String name) {
        ValidatorData.nameValidator(name);
        this.name = name.trim();
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public void removePlayer(String namePlayer) {
        boolean isRemoved = this.players.removeIf(p -> p.getName().equals(namePlayer));
        if(!isRemoved){
            throw new IllegalArgumentException("Player " + namePlayer + " is not in " + this.getName() + " team.");
        }
    }

    public double getRating() {
        return this.players.stream()
                 .mapToDouble(Player::overallSkillLevel).average().orElse(0.00);
    }

}
