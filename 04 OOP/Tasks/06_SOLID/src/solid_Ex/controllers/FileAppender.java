package solid_Ex.controllers;

import solid_Ex.enums.ReportLevel;
import solid_Ex.interfaces.CustomFile;
import solid_Ex.interfaces.Layout;

public class FileAppender extends AbstractAppender {
    CustomFile file = new LogFile();
    public FileAppender(ReportLevel reportLevel, Layout layout) {
        super(reportLevel, layout);
    }

    @Override
    protected String getType() {
        return "FileAppender";
    }

    @Override
    public void append(String date, ReportLevel reportLevel, String message) {
        String formatted = getLayout().format(date, reportLevel, message);
        file.write(formatted);
        super.incrementAppenderCount();
    }

    @Override
    public String toString() {
        return super.toString() + ", File size: "+ file.getSize();
    }
}
