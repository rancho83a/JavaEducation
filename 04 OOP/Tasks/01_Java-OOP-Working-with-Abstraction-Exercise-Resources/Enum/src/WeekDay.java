import java.time.DayOfWeek;

public enum WeekDay {
    MONDAY(1,"Monday"),
    TUE(2, "Tues"),
    WEDN(3,"Wednes"),
    THUR(4, "Thur");

    private int numericRepresentation;
    private String name;


    private WeekDay(int numeric, String name){
        this.numericRepresentation=numeric;
        this.name = name;
    }

    public int getNumericRepresentation() {
        return numericRepresentation;
    }

    public String getName() {
        return name;
    }
}
