package ge.tbc.testautomation.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class HomePage {

    public SelenideElement cookiePopUp() {
      return   $(".tbcx-pw-cookie-consent .tbcx-pw-cookie-consent__actions");
    }

    public SelenideElement cookieAcceptButton() {
        return $(".tbcx-pw-cookie-consent__actions button.primary");
    }

    public SelenideElement locationsNavLink() {
        return $("tbcx-pw-contacts a[href='/ka/atms&branches']");
    }

    public ElementsCollection navigationItems() {
        return $$(".tbcx-pw-navigation-item button");
    }

    public SelenideElement educationLink() {
        return $(".ng-star-inserted a[href='/ka/tbc-education']");
    }

    public SelenideElement burgerMenu = $(".tbcx-pw-hamburger-menu__button");
    public ElementsCollection burgerMenuSections = $$(".tbcx-pw-navigation-item button");
    public ElementsCollection EducationMenuOptions = $$(".tbcx-pw-mega-menu-sub-item__title");

    //currency exchange
    public SelenideElement currencyExchangeLink = $(".pane-container a[href='/ka/treasury-products']");
}