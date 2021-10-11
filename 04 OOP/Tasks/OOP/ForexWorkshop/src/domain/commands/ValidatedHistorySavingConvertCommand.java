package domain.commands;

import domain.Exceptions.InvalidCurrencyException;
import domain.external.CurrencyValidator;
import domain.external.ExchangeService;
import domain.io.Logger;
import domain.repository.ConversionHistoryRepository;

public class ValidatedHistorySavingConvertCommand  extends HistorySavingConvertCommand{
    private CurrencyValidator currencyValidator;

    public ValidatedHistorySavingConvertCommand(ExchangeService exchangeService, Logger logger,
                                                ConversionHistoryRepository repo,
                                                CurrencyValidator currencyValidator  ) {
        super(exchangeService, logger, repo);
        this.currencyValidator = currencyValidator;
    }

    @Override
    public void execute(Input input) {
            currencyValidator.validate(input.getFrom().getCurrency());
            currencyValidator.validate(input.getToCurrency());

        super.execute(input);
    }
}
