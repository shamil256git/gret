package converter;

import exchange.ExchangeService;

public class CurrencyConverter {
    private ExchangeService exchangeService;

    public CurrencyConverter(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    public double convert(String fromCurrency, String toCurrency, double amount) {
        // Validate input
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }

        if (fromCurrency == null || toCurrency == null) {
            throw new IllegalArgumentException("Currency codes cannot be null");
        }

        // Get exchange rate
        double rate = exchangeService.getExchangeRate(fromCurrency, toCurrency);

        // If rate not found
        if (rate == 0.0 && !fromCurrency.equals(toCurrency)) {
            return 0.0; // Conversion not possible
        }

        // Perform conversion
        return amount * rate;
    }
}