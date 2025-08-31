package ge.tbc.testautomation.steps;

import com.codeborne.selenide.SelenideElement;
import ge.tbc.testautomation.pages.CurrencyExchangePage;
import ge.tbc.testautomation.utils.CurrencyCalculatorHelper;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;

public class CurrencyExchangeSteps {
    CurrencyExchangePage exchangePage = new CurrencyExchangePage();
    CurrencyCalculatorHelper helper = new CurrencyCalculatorHelper();

    public List<SelenideElement> getPopularCurrencies() {
        exchangePage.popularCurrencies.shouldBe(sizeGreaterThan(0), Duration.ofSeconds(10));

        List<SelenideElement> popCurrencies = new ArrayList<>();
        for (SelenideElement item : exchangePage.popularCurrencies) {
            SelenideElement name = item.$(exchangePage.currencyNameLocator);
            popCurrencies.add(name);
        }
        return popCurrencies;
    }

    public String getRate(SelenideElement currency, String buyOrSell) {
        String rate = currency
                .closest(exchangePage.rowsLocator)
                .$$(exchangePage.captionsLocator)
                .findBy(text(buyOrSell))
                .$x(exchangePage.rateLocator)
                .getText();
        return rate;
    }


    public CurrencyExchangeSteps verifyCurrencyBuyCalculation(SelenideElement currency, String rate, String gel) {
        String currencyName = currency.getText();

        exchangePage.dropdownFrom.shouldBe(clickable).click();
        exchangePage.dropdownCurrencyList
                .findBy(innerText(currencyName)).shouldBe(visible).click();

        if (!exchangePage.dropdownTo.text().equals(gel)) {
            exchangePage.dropdownTo.shouldBe(clickable).click();
            exchangePage.dropdownCurrencyList
                    .findBy(innerText(gel)).shouldBe(visible).click();
        }
        exchangePage.rateShownOnCalculator.shouldHave(matchText(rate));

        return this;
    }
    public CurrencyExchangeSteps verifyCurrencySellCalculation(SelenideElement currency, String rate, String gel) {
        String currencyName = currency.getText();
        String rateForSell = helper.getSellRate(rate);

        if (!exchangePage.dropdownFrom.text().equals(gel)) {
            exchangePage.dropdownFrom.shouldBe(clickable).click();
            exchangePage.dropdownCurrencyList
                    .findBy(innerText(gel)).shouldBe(visible).click();
        }

        exchangePage.dropdownTo.shouldBe(clickable).click();
        exchangePage.dropdownCurrencyList
                .findBy(innerText(currencyName)).shouldBe(visible).click();
        exchangePage.rateShownOnCalculator.shouldHave(matchText(rateForSell), Duration.ofSeconds(10));

        return this;
    }
}
