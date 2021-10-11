package ExercisesSOLID.controllers;

import ExercisesSOLID.enums.ReportLevel;
import ExercisesSOLID.interfaces.Appender;
import ExercisesSOLID.interfaces.AppenderFactory;
import ExercisesSOLID.interfaces.Layout;

public class AppenderWorkshop implements AppenderFactory {


    @Override
    public Appender produce(String type, ReportLevel reportLevel, Layout layout) {

        Appender appender = null;
        switch (type) {
            case "ConsoleAppender":
                appender = new ConsoleAppender(reportLevel,layout);
                break;
            case "FileAppender":
                appender = new FileAppender(reportLevel,layout);
                break;
            default:
                throw new IllegalStateException("Not valid type for appender" + type + "param");
        }

        return appender;
    }
}
