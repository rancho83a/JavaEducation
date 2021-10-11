package solid_Ex.interfaces;

import solid_Ex.enums.ReportLevel;

public interface Appender {
    void append(String date, ReportLevel reportLevel, String message);

    ReportLevel getReportLevel();
}
