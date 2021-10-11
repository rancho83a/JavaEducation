package militaryElite.enums;

public enum Corp {
    AIRFORCES("Airforces"),
    MARINES("Marines");

    private String type;

    Corp(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
