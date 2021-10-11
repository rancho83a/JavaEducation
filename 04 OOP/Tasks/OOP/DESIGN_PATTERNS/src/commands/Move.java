package commands;

public class Move implements Command {
    private int param;
    @Override
    public String execute() {
        return "Moved by "+param+" meters";
    }

    @Override
    public void setCommandValue(Integer value) {
        this.param=value;

    }
}
