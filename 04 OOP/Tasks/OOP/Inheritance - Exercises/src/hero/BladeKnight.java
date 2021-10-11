package hero;

public class BladeKnight extends DarkKnight {
    public BladeKnight(String username, int level) {
        super(username, level);
    }

    @Override
    public String toString() {
        return "Type: BladeKnight" + super.toString();

    }
}
