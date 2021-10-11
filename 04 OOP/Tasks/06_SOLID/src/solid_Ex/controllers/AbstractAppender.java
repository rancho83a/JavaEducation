package solid_Ex.controllers;

import solid_Ex.enums.ReportLevel;
import solid_Ex.interfaces.Appender;
import solid_Ex.interfaces.Layout;

public abstract class AbstractAppender implements Appender {
    private Layout layout;
    private ReportLevel reportLevel;
    private int appenderCount;

    public AbstractAppender(ReportLevel reportLevel, Layout layout) {
        this.reportLevel = reportLevel;
        this.layout = layout;
    }

    public  AbstractAppender(Layout layout){
        this(ReportLevel.INFO, layout);
    }

    protected Layout getLayout() {
        return layout;
    }

    protected abstract String getType();
    protected void incrementAppenderCount(){
        this.appenderCount++;
    }

    @Override
    public ReportLevel getReportLevel() {
        return this.reportLevel;
    }

    @Override
    public String toString() {
        //Appender type: ConsoleAppender, Layout type: SimpleLayout, Report level: CRITICAL, Messages appended: 2
        StringBuilder sb = new StringBuilder("Appender type: ");
                sb.append(this.getType())
                .append(", Layout type: ")
                .append(this.layout.getType())
                .append(", Report level: ")
                .append(this.reportLevel.toString())
                .append(", Messages appended: ")
                .append(this.appenderCount);
        return sb.toString();
    }
}
