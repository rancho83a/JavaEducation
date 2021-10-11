package console;

import domain.commands.*;
import domain.entities.Money;
import domain.external.CurrencyValidator;
import domain.external.ExchangeService;
import domain.io.Logger;
import domain.repository.ConversionHistoryRepository;
import external.CurrConvExchangeService;
import external.CurrencyValidatorFromFile;
import external.StubbedExchangeService;
import repository.InMemoryConversionHistoryRepository;

import java.nio.file.Path;
import java.util.Scanner;

public class ConsoleRunner {

    private ExchangeService exchangeService = new CurrConvExchangeService();
    private Logger logger = new ConsoleLogger();
    private ConversionHistoryRepository conversionHistoryRepository = new InMemoryConversionHistoryRepository();
    private CurrencyValidator currencyValidator = new CurrencyValidatorFromFile(Path.of("D:\\currency.txt"));


    ConsoleCommanderExecutor executor = new ConsoleCommanderExecutor(exchangeService, logger,
            conversionHistoryRepository, currencyValidator);

    public void run() {
        Scanner scan = new Scanner(System.in);
        while (true) {
            String input = scan.nextLine();
            String[] tokens = input.split("\\s+");
            executor.execute(tokens);
        }
    }

}
