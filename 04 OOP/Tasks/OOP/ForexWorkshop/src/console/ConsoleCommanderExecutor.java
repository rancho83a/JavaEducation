package console;

import domain.commands.*;
import domain.entities.Money;
import domain.external.CurrencyValidator;
import domain.external.ExchangeService;
import domain.io.Logger;
import domain.repository.ConversionHistoryRepository;

import java.math.BigDecimal;

public class ConsoleCommanderExecutor {

    private ExchangeService exchangeService;
    private Logger logger;
    private ConversionHistoryRepository conversionHistoryRepository;
    private CurrencyValidator currencyValidator;

    public ConsoleCommanderExecutor(ExchangeService exchangeService, Logger logger,
                                    ConversionHistoryRepository conversionHistoryRepository,
                                    CurrencyValidator currencyValidator) {
        this.exchangeService = exchangeService;
        this.logger = logger;
        this.conversionHistoryRepository = conversionHistoryRepository;
        this.currencyValidator = currencyValidator;
    }

    public void execute(String[] tokens) {

        switch (tokens[0]) {
            case "END":
                end();
                break;
            case "CONVERT":
                convert(tokens);
                break;
            case "HISTORY":
                history(tokens);
                break;
            default:
                logger.logLine("Incorrect command");
        }

    }

    private void history(String[] tokens) {
        Command<HistoryCommand.Input> cmd = new HistoryCommand(conversionHistoryRepository, logger);
        cmd.execute(new HistoryCommand.Input(Integer.parseInt(tokens[1])));
    }

    private void convert(String[] tokens) {
        BigDecimal value = new BigDecimal(tokens[1]);
        String currencyFrom = tokens[2];
        String currencyTo = tokens[3];

        ConvertCommand.Input input =
                new ConvertCommand.Input(
                        new Money(value, currencyFrom),
                        currencyTo
                );
        new ValidatedHistorySavingConvertCommand(exchangeService,
                logger,
                conversionHistoryRepository,
                currencyValidator
        ).execute(input);

    }

    private void end() {
        new EndCommand().execute(new EmptyInput());
    }
}
