package militaryElite.Interfaces;

import militaryElite.entities.Repair;

import java.util.Collection;

public interface Engineer {
    void addRepair(Repair repair);
    Collection<Repair> getRepairs();
}
