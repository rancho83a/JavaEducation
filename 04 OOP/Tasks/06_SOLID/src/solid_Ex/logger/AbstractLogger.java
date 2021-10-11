package solid_Ex.logger;

import solid_Ex.enums.ReportLevel;
import solid_Ex.interfaces.Appender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AbstractLogger implements  Logger{
    private List<Appender> appenders;

    public AbstractLogger(Appender... appenders) {
        this.appenders = new ArrayList<>(Arrays.asList(appenders));
    }

    public void addAppender(Appender appender){
        this.appenders.add(appender);
    }

    @Override
    public void logInfo(String date, String message) {
        callAllAppenders(date, ReportLevel.INFO, message);

    }

    @Override
    public void logWarning(String date, String message) {
        callAllAppenders(date, ReportLevel.WARNING, message);

    }

    @Override
    public void logError(String date, String message) {
        callAllAppenders(date, ReportLevel.ERROR, message);

    }

    @Override
    public void logCritical(String date, String message) {
        callAllAppenders(date, ReportLevel.CRITICAL, message);

    }

    @Override
    public void logFatal(String date, String message) {
        callAllAppenders(date, ReportLevel.FATAL, message);
    }

    private void callAllAppenders(String date, ReportLevel reportLevel, String message) {
        for (Appender appender : appenders) {
            if(appender.getReportLevel().ordinal()<=reportLevel.ordinal()) {
                appender.append(date, reportLevel, message);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Logger info");
        sb.append(System.lineSeparator());

        sb.append(this.appenders.stream().map(Object::toString)
        .collect(Collectors.joining(System.lineSeparator())));
        return sb.toString();
    }
}
