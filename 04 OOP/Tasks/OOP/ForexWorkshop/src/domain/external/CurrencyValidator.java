package domain.external;

import domain.Exceptions.InvalidCurrencyException;

public interface CurrencyValidator {

    void  validate (String currency) throws InvalidCurrencyException;
}
