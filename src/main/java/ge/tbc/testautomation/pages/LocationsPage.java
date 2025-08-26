package ge.tbc.testautomation.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class LocationsPage {

    public SelenideElement mapWrapper() {
        return $(".tbcx-pw-atm-branches-section__map-wrapper .tbcx-pw-atm-branches-section__list-wrapper");
    }

    public ElementsCollection tabButtons() {
        return $$(".tbcx-pw-tab-menu button");
    }

    public ElementsCollection branchListItems() {
        return $$("app-atm-branches-section-list-item");
    }

    public SelenideElement filterDropdown() {
        return $("div[class='tbcx-dropdown-selector']");
    }

    public ElementsCollection filterOptions() {
        return $$(".tbcx-dropdown-popover-item__title");
    }

    public SelenideElement searchInput() {
        return $("#tbcx-text-input-1");
    }

    public ElementsCollection markers() {
        return $$("gmp-advanced-marker");
    }
}
