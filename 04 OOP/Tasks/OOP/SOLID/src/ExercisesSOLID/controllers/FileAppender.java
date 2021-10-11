package ExercisesSOLID.controllers;

import ExercisesSOLID.enums.ReportLevel;
import ExercisesSOLID.interfaces.File;
import ExercisesSOLID.interfaces.Layout;

public class FileAppender extends AbstractAppender {
    private File file;

    public FileAppender(ReportLevel reportLevel, Layout layout) {
        super(reportLevel,layout);
        this.file = new LogFile();
    }

    @Override
    protected String getType() {
        return "FileAppender";
    }


    @Override
    public void append(String date, ReportLevel reportLevel, String message) {
        String formatted = this.getLayout().format(date, reportLevel, message);
        this.file.write(formatted);
        super.incrementAppendCounter();
    }

    @Override
    public String toString() {
        return super.toString() + ", File size: " + this.file.getFileSize();
        
    }


}
