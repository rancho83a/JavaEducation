package solid_Ex.interfaces;

import solid_Ex.enums.ReportLevel;

public interface Layout {
    String format(String date, ReportLevel reportLevel, String message);

    String getType();
}
