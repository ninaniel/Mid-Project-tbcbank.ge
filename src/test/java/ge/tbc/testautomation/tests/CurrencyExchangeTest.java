package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.pages.CurrencyExchangePage;
import ge.tbc.testautomation.runners.BaseTest;
import ge.tbc.testautomation.steps.CurrencyExchangeSteps;
import ge.tbc.testautomation.steps.HomeSteps;

import org.testng.annotations.Test;


public class CurrencyExchangeTest extends BaseTest {
    HomeSteps homeSteps = new HomeSteps();
    CurrencyExchangeSteps currencyExchangeSteps = new CurrencyExchangeSteps();

    @Test
    public void popularCurrenciesExchangeWithGEL(){
        homeSteps
                .navigateToCurrencyExchangePage();

        for (var currency : currencyExchangeSteps.getPopularCurrencies()) {
            String buyRate = currencyExchangeSteps
                    .getRate(currency, Constants.TO_BUY);
            String sellRate = currencyExchangeSteps
                    .getRate(currency, Constants.TO_SELL);

            currencyExchangeSteps
                    .verifyCurrencyBuyCalculation(currency, buyRate, Constants.GEO_CURRENCY)
                    .verifyCurrencySellCalculation(currency, sellRate, Constants.GEO_CURRENCY);
        }
    }
}
