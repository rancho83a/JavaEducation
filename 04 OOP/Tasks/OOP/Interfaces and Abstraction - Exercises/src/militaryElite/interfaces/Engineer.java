package militaryElite.interfaces;


import militaryElite.entities.Repair;

import java.util.Collection;

public interface Engineer {
    void addRepair(Repair repair);
    Collection<Repair> getRepairs();
}
