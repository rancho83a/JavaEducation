package birthdayCelebrations;

public class Robot implements Identifiable {
    private String id;

    public Robot(String id, String model) {
        this.id = id;
        this.model = model;
    }

    private String model;

    public String getModel() {
        return model;
    }

    @Override
    public String getId() {
        return this.id;
    }
}
