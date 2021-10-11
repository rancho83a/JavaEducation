package ExercisesSOLID.controllers;

import ExercisesSOLID.enums.ReportLevel;
import ExercisesSOLID.interfaces.Appender;
import ExercisesSOLID.interfaces.Layout;

public abstract class AbstractAppender implements Appender {

    private Layout layout;
    private ReportLevel reportLevel;
    private int appendCounter;

    public AbstractAppender(ReportLevel reportLevel, Layout layout) {
        this.reportLevel = reportLevel;
        this.layout = layout;
    }

    public AbstractAppender(Layout layout) {
        this(ReportLevel.INFO, layout);
    }


    protected Layout getLayout() {
        return layout;
    }

    @Override
    public ReportLevel getReportLevel() {
        return this.reportLevel;
    }

    protected abstract String getType();

    protected void incrementAppendCounter() {
        this.appendCounter++;
    }

    public String toString() {
        //Appender type: ConsoleAppender, Layout type: SimpleLayout, Report level: CRITICAL, Messages appended: 2
        StringBuilder sb = new StringBuilder("Appender type: ");
        sb.append(this.getType())
                .append(", Layout type: ")
                .append(this.layout.getType())
                .append(", Report level: ")
                .append(this.reportLevel.toString())
                .append(", Messages appended: ")
                .append(appendCounter);

        return sb.toString();
    }
}
