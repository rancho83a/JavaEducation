package militaryElite.enums;

public enum State {

    INPROGRESS("inProgress"), FINISHED("Finished");

    private String status;

    State(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
