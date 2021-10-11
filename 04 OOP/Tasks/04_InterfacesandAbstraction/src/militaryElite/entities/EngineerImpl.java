package militaryElite.entities;

import militaryElite.Interfaces.Engineer;
import militaryElite.enums.Corp;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class EngineerImpl extends SpecialisedSoldierImpl implements Engineer {

    private Set<Repair> repairs;

    public EngineerImpl(int id, String firstName, String lastName, double salary, Corp corp) {
        super(id, firstName, lastName, salary, corp);
        this.repairs = new LinkedHashSet<>();
    }

    @Override
    public void addRepair(Repair repair) {
        repairs.add(repair);
    }

    @Override
    public Collection<Repair> getRepairs() {
        return Collections.unmodifiableCollection(repairs);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append(System.lineSeparator()).append("Corps: ").append(this.getCorp().getType()).append(System.lineSeparator())
                .append("Repairs:");
        repairs.forEach(p->  sb.append(String.format("%n  %s",p.toString())));


        return sb.toString();
    }
}
