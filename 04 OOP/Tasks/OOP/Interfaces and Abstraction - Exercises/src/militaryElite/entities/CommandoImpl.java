package militaryElite.entities;

import militaryElite.enums.Corp;
import militaryElite.interfaces.Commando;

import java.util.*;

public class CommandoImpl extends SpecialisedSoldierImpl implements Commando {
    private Set<Mission> missions;


    public CommandoImpl(int id, String firstName, String lastName, double salary, Corp corps) {
        super(id, firstName, lastName, salary, corps);
        this.missions = new LinkedHashSet<>();
    }


    @Override
    public void addMission(Mission mission) {
        this.missions.add(mission);

    }

    @Override
    public Collection<Mission> getMissions() {
        return Collections.unmodifiableCollection(missions);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append(System.lineSeparator()).append("Corps: ").append(this.getCorp().getName()).append(System.lineSeparator())
                .append("Missions:");
        missions.forEach(p -> sb.append(String.format("%n  %s", p.toString())));
        return sb.toString();
    }
}
