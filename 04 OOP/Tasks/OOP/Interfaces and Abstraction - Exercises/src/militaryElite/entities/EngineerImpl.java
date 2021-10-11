package militaryElite.entities;

import militaryElite.enums.Corp;
import militaryElite.interfaces.Engineer;
import militaryElite.interfaces.Private;

import java.util.*;

public class EngineerImpl extends SpecialisedSoldierImpl implements Engineer {

    private Set<Repair> repairs;


    public EngineerImpl(int id, String firstName, String lastName, double salary, Corp corps) {
        super(id, firstName, lastName, salary, corps);
        this.repairs = new LinkedHashSet<>();
    }

    @Override
    public void addRepair(Repair repair) {
        this.repairs.add(repair);

    }

    @Override
    public Collection<Repair> getRepairs() {
        return Collections.unmodifiableCollection(this.repairs);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append(System.lineSeparator()).append("Corps: ").append(this.getCorp().getName()).append(System.lineSeparator())
                .append("Repairs:");
        repairs.forEach(p -> sb.append(String.format("%n  %s", p.toString())));
        return sb.toString();
    }
}
