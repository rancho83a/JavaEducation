package militaryElite.entities;

import militaryElite.Interfaces.Commando;
import militaryElite.enums.Corp;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class CommandoImpl extends SpecialisedSoldierImpl implements Commando {
    private Set<Mission> missions;

    public CommandoImpl(int id, String firstName, String lastName, double salary, Corp corp) {
        super(id, firstName, lastName, salary, corp);
        this.missions = new LinkedHashSet<>();
    }

    @Override
    public void addMission(Mission mission) {
        missions.add(mission);
    }

    @Override
    public Collection<Mission> getMissions() {
        return Collections.unmodifiableCollection(missions);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append(System.lineSeparator()).append("Corps: ").append(this.getCorp().getType()).append(System.lineSeparator())
                .append("Missions:");
        missions.forEach(p->  sb.append(String.format("%n  %s",p.toString())));


        return sb.toString();
    }
}
