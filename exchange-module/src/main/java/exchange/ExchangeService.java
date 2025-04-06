package exchange;

import java.util.HashMap;
import java.util.Map;

public class ExchangeService {
    private Map<String, Double> exchangeRates;

    public ExchangeService() {
        // Initialize with some default rates
        exchangeRates = new HashMap<>();

        // USD to other currencies
        exchangeRates.put("USD-EUR", 0.85);
        exchangeRates.put("USD-GBP", 0.73);
        exchangeRates.put("USD-JPY", 110.15);

        // EUR to other currencies
        exchangeRates.put("EUR-USD", 1.18);
        exchangeRates.put("EUR-GBP", 0.86);
        exchangeRates.put("EUR-JPY", 129.55);

        // GBP to other currencies
        exchangeRates.put("GBP-USD", 1.37);
        exchangeRates.put("GBP-EUR", 1.16);
        exchangeRates.put("GBP-JPY", 150.75);

        // JPY to other currencies (using small decimals since 1 JPY is small)
        exchangeRates.put("JPY-USD", 0.009);
        exchangeRates.put("JPY-EUR", 0.0077);
        exchangeRates.put("JPY-GBP", 0.0066);
    }
