package solid_Ex.interfaces;

import solid_Ex.enums.ReportLevel;

public interface AppenderFactory {

    Appender produce(String type, ReportLevel reportLevel, Layout layout);
}
