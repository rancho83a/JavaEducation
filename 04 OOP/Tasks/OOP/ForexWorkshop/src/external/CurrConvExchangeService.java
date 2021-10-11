package external;

import domain.entities.Money;
import domain.external.ExchangeService;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CurrConvExchangeService implements ExchangeService {
    private static final String KEY_API = System.getenv("API_KEY");


    @Override
    public Money exchange(Money from, String toCurrency) {
        BigDecimal exchangeRate = fetchExchangeRateFor(from.getCurrency(), toCurrency);
        return new Money(from.getValue().multiply(exchangeRate), toCurrency);
    }

    private BigDecimal fetchExchangeRateFor(String currencyFrom, String currencyTo) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(exchangeRateUrlForCurrencies(currencyFrom, currencyTo))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return extractExchangeRate(response.body());
        } catch (Exception e) {
            throw new RuntimeException("something went wrong during Http request");
        }
    }

    private BigDecimal extractExchangeRate(String response) {

        int startIndex = response.lastIndexOf(":") + 1;
        int endIndex = response.lastIndexOf("}");
        String exchangeRate = response.substring(startIndex, endIndex);
        return new BigDecimal(exchangeRate);
    }

    private URI exchangeRateUrlForCurrencies(String currencyFrom, String currencyTo) {
        String url = String.format("https://free.currconv.com/api/v7/convert?q=%s_%s&compact=ultra&apiKey=%s",
                currencyFrom, currencyTo, KEY_API);
        try {

            return new URI(url);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("failed to build URI");
        }
    }
}
