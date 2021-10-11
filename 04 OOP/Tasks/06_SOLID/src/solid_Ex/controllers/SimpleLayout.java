package solid_Ex.controllers;

import solid_Ex.enums.ReportLevel;
import solid_Ex.interfaces.Layout;

public class SimpleLayout implements Layout {
    @Override
    public String format(String date, ReportLevel reportLevel, String message) {
        return String.format("%s - %s - %s", date, reportLevel.toString(), message);
    }

    @Override
    public String getType() {
        return "SimpleLayout";
    }
}
