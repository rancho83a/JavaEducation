package solid_Ex.core;

import solid_Ex.controllers.FactoryRepository;
import solid_Ex.enums.ReportLevel;
import solid_Ex.interfaces.Appender;
import solid_Ex.interfaces.Engine;
import solid_Ex.interfaces.InputParser;
import solid_Ex.interfaces.Layout;
import solid_Ex.logger.Logger;

import java.io.BufferedReader;
import java.io.IOException;

public class EngineImpl implements Engine {
    private boolean isRunning;
    private BufferedReader reader;
    private Logger logger;

    public EngineImpl(BufferedReader reader, Logger logger) {
        this.reader = reader;
        this.logger=logger;
    }

    @Override
    public void run() throws IOException {
        this.isRunning = true;
        String line = reader.readLine();
        addAppenders(Integer.parseInt(line));

        while (this.isRunning) {
            line = reader.readLine();
            String[] parse = InputParser.parse(line);

            if(!parse[0].equals("END")) {
                logMessage(parse);
            }
            this.isRunning = !parse[0].equals("END");
        }
    }

    private void logMessage(String[] args) {
        ReportLevel reportLevel = ReportLevel.valueOf(args[0]);

        String date = args[1];
        String messsage = args[2];
        switch (reportLevel) {
            case INFO -> logger.logInfo(date, messsage);
            case WARNING -> logger.logWarning(date, messsage);
            case CRITICAL -> logger.logCritical(date, messsage);
            case ERROR -> logger.logError(date, messsage);
            case FATAL -> logger.logFatal(date, messsage);
            default -> throw new IllegalStateException("...");
        }

    }

    private void addAppenders(int n) throws IOException {
        while (n-- > 0) {
            String[] tokens = reader.readLine().split("\\s+");
            ReportLevel reportLevel = tokens.length == 3 ? ReportLevel.valueOf(tokens[2].toUpperCase()) : ReportLevel.INFO;
            Layout layout = FactoryRepository.getLayoutFactory().produce(tokens[1]);
            Appender appender = FactoryRepository.getAppenderFactory().produce(tokens[0], reportLevel, layout);
            logger.addAppender(appender);
        }
    }
}
