package militaryElite.interfaces;

import militaryElite.entities.Mission;

import java.util.Collection;

public interface Commando {
    void addMission(Mission mission);

    Collection<Mission> getMissions();
}
