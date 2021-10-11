package ExercisesSOLID.interfaces;

import ExercisesSOLID.enums.ReportLevel;

public interface Layout {

    String format(String date, ReportLevel reportlevel, String message);

    String getType();
}
