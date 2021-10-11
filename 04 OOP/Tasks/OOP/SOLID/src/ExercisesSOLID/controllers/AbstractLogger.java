package ExercisesSOLID.controllers;

import ExercisesSOLID.enums.ReportLevel;
import ExercisesSOLID.interfaces.Appender;
import ExercisesSOLID.interfaces.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractLogger implements Logger {
    private List<Appender> appenders;

    public AbstractLogger(Appender... appenders) {
        this.appenders = new ArrayList<>(Arrays.asList(appenders));
    }

    private void callAllApenders(String date, ReportLevel reportLevel, String message) {
        for (Appender appender : appenders) {
            if (appender.getReportLevel().ordinal() <= reportLevel.ordinal()) {
                appender.append(date, reportLevel, message);
            }
        }
    }

    @Override
    public void logInfo(String date, String message) {
        callAllApenders(date, ReportLevel.INFO, message);

    }

    @Override
    public void logWarning(String date, String message) {
        callAllApenders(date, ReportLevel.WARNING, message);

    }

    @Override
    public void logError(String date, String message) {
        callAllApenders(date, ReportLevel.ERROR, message);

    }

    @Override
    public void logCritical(String date, String message) {
        callAllApenders(date, ReportLevel.CRITICAL, message);

    }

    @Override
    public void logFatal(String date, String message) {
        callAllApenders(date, ReportLevel.FATAL, message);
    }

    @Override
    public void add(Appender appender) {
        this.appenders.add(appender);
    }

    @Override
    public String toString() {
        StringBuilder sb =new StringBuilder("Logger info").append(System.lineSeparator());

        sb.append(this.appenders.stream().map(Appender::toString).collect(Collectors.joining(System.lineSeparator())));

        return sb.toString();
    }
}
