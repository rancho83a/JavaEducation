package ExercisesSOLID.core;

import ExercisesSOLID.controllers.FactoryRepository;
import ExercisesSOLID.enums.ReportLevel;
import ExercisesSOLID.interfaces.Appender;
import ExercisesSOLID.interfaces.Engine;
import ExercisesSOLID.interfaces.InputParser;
import ExercisesSOLID.interfaces.Logger;

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

        String line = reader.readLine();
        addApenders(Integer.parseInt(line));

        this.isRunning = true;
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
        String message = args[2];

        switch (reportLevel) {
            case INFO -> logger.logInfo(date, message);
            case FATAL -> logger.logFatal(date, message);
            case CRITICAL -> logger.logCritical(date, message);
            case WARNING -> logger.logWarning(date, message);
            case ERROR -> logger.logError(date, message);

        }
    }

    private void addApenders(int n) throws IOException {
        while (n-- > 0) {
            String[] tokens = reader.readLine().split("\\s+");
            ReportLevel reportLevel = tokens.length == 3
                    ? ReportLevel.valueOf(tokens[2].toUpperCase())
                    : ReportLevel.INFO;

            Appender appender = FactoryRepository.getAppenderFactory().produce(tokens[0], reportLevel,
                    FactoryRepository.getLayoutFactory().produce(tokens[1]));
            logger.add(appender);
        }

    }
}
