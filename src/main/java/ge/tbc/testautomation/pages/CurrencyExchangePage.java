package ge.tbc.testautomation.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class CurrencyExchangePage {
    public SelenideElement popularCurrenciesWrapper = $(".tbcx-pw-popular-currencies__rows");
    public ElementsCollection popularCurrencies = popularCurrenciesWrapper.$$(".tbcx-pw-popular-currencies__row");
    public String currencyNameLocator = ".tbcx-pw-currency-badge";
    public SelenideElement dropdownFrom = $$(".tbcx-dropdown-selector .tbcx-selection-text").first();
    public SelenideElement dropdownTo = $$(".tbcx-dropdown-selector .tbcx-selection-text").last();
    public ElementsCollection dropdownCurrencyList = $$(".tbcx-item-list .tbcx-dropdown-popover-item");
    public SelenideElement rateShownOnCalculator = $(".tbcx-pw-exchange-rates-calculator__description");

    public String rowsLocator = (".tbcx-pw-popular-currencies__row");
    public String captionsLocator = (".tbcx-pw-popular-currencies__caption");
    public String rateLocator = ("following-sibling::div[@class='tbcx-pw-popular-currencies__body']");



}
