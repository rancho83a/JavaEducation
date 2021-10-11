package solid_Ex.controllers;

import solid_Ex.enums.ReportLevel;
import solid_Ex.interfaces.Appender;
import solid_Ex.interfaces.Layout;

public class XmlLayout implements Layout {


    @Override
    public String format(String date, ReportLevel reportLevel, String message) {
        return String.format("<log>%n" +
                "<date>%s</date>%n" +
                "<level>%s</level>%n" +
                "<message>%s</message>%n" +
                "</log>", date, reportLevel.toString(), message);
    }

    @Override
    public String getType() {
        return "XmlLayout";
    }
}
