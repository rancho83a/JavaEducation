package militaryElite.entities;

import militaryElite.Interfaces.Spy;

public class SpyImpl extends SoldierImpl  implements Spy {
    private String codeNumber;
    public SpyImpl(int id, String firstName, String lastName, String codeNumber) {
        super(id, firstName, lastName);
        this.codeNumber=codeNumber;
    }

    @Override
    public String getCodeNumber() {
        return codeNumber;
    }

    @Override
    public String toString() {
        return super.toString()+ String.format("%nCode Number: %s",codeNumber);
    }
}
