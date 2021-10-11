package commands;

public class Attack implements Command {
    private Integer value;
    @Override
    public String execute() {
        return "Target defeated XP won " +this.value ;
    }

    @Override
    public void setCommandValue(Integer value) {
        this.value = value;
    }
}
