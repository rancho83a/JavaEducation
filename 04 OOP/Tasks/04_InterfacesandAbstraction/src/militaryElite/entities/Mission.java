package militaryElite.entities;


import militaryElite.enums.State;

public class Mission {
    private String codeName;
    private State state;

    public Mission(String codeName, State state) {
        this.codeName = codeName;
        this.state = state;
    }

    public String getCodeName() {
        return codeName;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void completeMission() {
        this.setState(State.FINISHED);
    }
    @Override
    public String toString() {
        return String.format("Code Name: %s State: %s",this.getCodeName(), this.getState().getStatus());
    }
}
