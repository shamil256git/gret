package converter;

import exchange.ExchangeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CurrencyConverterTest {
    private ExchangeService exchangeService;
    private CurrencyConverter converter;

    @BeforeEach
    void setUp() {
        // Create a fresh exchange service for each test
        exchangeService = new ExchangeService();

        // Initialize the converter with the exchange service
        converter = new CurrencyConverter(exchangeService);
    }

    @Test
    void testBasicConversion_UsdToEur() {
        // Setup
        String fromCurrency = "USD";
        String toCurrency = "EUR";
        double amount = 100.0;

        // Execute
        double actualResult = converter.convert(fromCurrency, toCurrency, amount);

        // Verify
        double expectedResult = 85.0; // 100 USD should be 85 EUR
        assertEquals(expectedResult, actualResult, 0.001);
    }

    @Test
    void testBasicConversion_EurToUsd() {
        // Setup
        String fromCurrency = "EUR";
        String toCurrency = "USD";
        double amount = 50.0;

        // Execute
        double actualResult = converter.convert(fromCurrency, toCurrency, amount);

        // Verify
        double expectedResult = 59.0; // 50 EUR should be 59 USD
        assertEquals(expectedResult, actualResult, 0.001);
    }

    @Test
    void testConversion_UsdToJpy() {
        // Setup
        String fromCurrency = "USD";
        String toCurrency = "JPY";
        double amount = 200.0;

        // Execute
        double actualResult = converter.convert(fromCurrency, toCurrency, amount);

        // Verify
        double expectedResult = 22030.0; // 200 USD should be 22030 JPY
        assertEquals(expectedResult, actualResult, 0.1);
    }

    @Test
    void testSameCurrencyConversion() {
        // Setup
        String currency = "USD";
        double amount = 100.0;

        // Execute
        double actualResult = converter.convert(currency, currency, amount);

        // Verify
        double expectedResult = 100.0; // Same currency should return same amount
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testUnsupportedCurrencyPair() {
        // Setup
        String fromCurrency = "AUD"; // Australian Dollar - not in our rates
        String toCurrency = "CAD"; // Canadian Dollar - not in our rates
        double amount = 100.0;

        // Execute
        double actualResult = converter.convert(fromCurrency, toCurrency, amount);

        // Verify
        double expectedResult = 0.0; // Unsupported should return 0
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testNegativeAmount() {
        // Setup
        String fromCurrency = "USD";
        String toCurrency = "EUR";
        double amount = -100.0;

        // Execute & Verify
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            converter.convert(fromCurrency, toCurrency, amount);
        });

        String expectedMessage = "Amount cannot be negative";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testNullCurrency() {
        // Setup
        String fromCurrency = null;
        String toCurrency = "EUR";
        double amount = 100.0;

        // Execute & Verify
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            converter.convert(fromCurrency, toCurrency, amount);
        });

