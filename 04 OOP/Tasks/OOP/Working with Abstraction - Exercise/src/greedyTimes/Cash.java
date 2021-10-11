package greedyTimes;

public class Cash extends BaseEntity {
    private String name;

    public Cash(String name,long value) {
        super(value);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
