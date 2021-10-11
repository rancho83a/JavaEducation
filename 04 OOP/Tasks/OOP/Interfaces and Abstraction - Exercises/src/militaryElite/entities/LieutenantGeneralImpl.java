package militaryElite.entities;

import militaryElite.interfaces.LieutenantGeneral;
import militaryElite.interfaces.Soldier;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LieutenantGeneralImpl extends PrivateImpl implements LieutenantGeneral {

    private List<PrivateImpl> privates;

    public LieutenantGeneralImpl(int id, String firstName, String lastName, double salary) {
        super(id, firstName, lastName, salary);
        this.privates = new ArrayList<>();
    }

    @Override
    public void addPrivate(PrivateImpl priv) {
        this.privates.add(priv);

    }

    @Override
    public List<PrivateImpl> getPrivates() {
        return this.privates;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append(System.lineSeparator()).append("Privates:");
        this.getPrivates().sort(Comparator.comparingInt(Soldier::getId).reversed());
        this.getPrivates().forEach(p -> sb.append(String.format("%n  %s", p.toString())));
        return sb.toString();
    }
}
