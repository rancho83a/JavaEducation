package external;

import domain.Exceptions.InvalidCurrencyException;
import domain.external.CurrencyValidator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CurrencyValidatorFromFile implements CurrencyValidator {
    private Path pathFile;

    public CurrencyValidatorFromFile(Path pathFile) {
        this.pathFile = pathFile;
    }

    @Override
    public void validate(String currency) throws InvalidCurrencyException {
        try {
            Files.lines(pathFile)
                    .filter(currency::equals)
                    .findAny()
                    .orElseThrow(InvalidCurrencyException::new);
        } catch (IOException exception) {
            throw  new InvalidCurrencyException();
        }
    }
}
