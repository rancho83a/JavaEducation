package solid_Ex.controllers;

import solid_Ex.enums.ReportLevel;
import solid_Ex.interfaces.Appender;
import solid_Ex.interfaces.AppenderFactory;
import solid_Ex.interfaces.Layout;

public class AppenderWorkshop implements AppenderFactory {
    @Override
    public Appender produce(String type, ReportLevel reportLevel, Layout layout) {
        return switch (type) {
            case "ConsoleAppender" -> new ConsoleAppender(reportLevel, layout);
            case "FileAppender" -> new FileAppender(reportLevel, layout);
            default -> throw new IllegalArgumentException("Invalid type of appender " + type);
        };
    }
}
