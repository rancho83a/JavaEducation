package ExercisesSOLID.controllers;

import ExercisesSOLID.enums.ReportLevel;
import ExercisesSOLID.interfaces.Layout;

public class XmlLayout implements Layout {
    @Override
    public String format(String date, ReportLevel reportlevel, String message) {

        return String.format("<log>\r\n" +
                "   <date>%s</date>\n" +
                "   <level>%s</level>\n" +
                "   <message>%s</message>\n" +
                "</log>", date, reportlevel, message);
    }

    @Override
    public String getType() {
        return "XmlLayout";
    }
}
