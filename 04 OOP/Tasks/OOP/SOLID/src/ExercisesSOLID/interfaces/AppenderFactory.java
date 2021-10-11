package ExercisesSOLID.interfaces;

import ExercisesSOLID.enums.ReportLevel;

public interface AppenderFactory {
    Appender produce(String Type, ReportLevel reportLevel, Layout layout);
}
