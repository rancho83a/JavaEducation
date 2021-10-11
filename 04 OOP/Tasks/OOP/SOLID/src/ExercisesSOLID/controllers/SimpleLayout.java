package ExercisesSOLID.controllers;

import ExercisesSOLID.enums.ReportLevel;
import ExercisesSOLID.interfaces.Layout;

public class SimpleLayout implements Layout {

    @Override
    public String format(String date, ReportLevel reportlevel, String message) {
        return String.format("%s - %s - %s",date,reportlevel,message );
    }

    @Override
    public String getType() {
        return "SimpleLayout";
    }
}
