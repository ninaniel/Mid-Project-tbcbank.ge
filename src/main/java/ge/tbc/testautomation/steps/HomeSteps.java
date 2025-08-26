package ge.tbc.testautomation.steps;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import ge.tbc.testautomation.pages.HomePage;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;

public class HomeSteps {

    HomePage homePage = new HomePage();

    public HomeSteps openHomePage() {
        Selenide.open("https://tbcbank.ge/ka/");
        return this;
    }

    public HomeSteps acceptCookiesIfPresent() {
        homePage.cookieAcceptButton().shouldBe(clickable).click();
        return this;
    }

    public HomeSteps navigateToLocations() {
        homePage.locationsNavLink().shouldBe(visible).click();
        return this;
    }

    public HomeSteps navigateToEducationDesktop() {
        homePage.navigationItems().findBy(matchText("თიბისი")).hover();
        homePage.educationLink().shouldBe(visible).click();
        return this;
    }

    // Mobile: burger menu
    public HomeSteps openBurgerMenu() {
        homePage.burgerMenu.click();
        return this;
    }

    public HomeSteps openTbcSectionMobile(String sectionName) {
        homePage.burgerMenuSections.findBy(matchText(sectionName)).click();
        return this;
    }

    public HomeSteps openMegaMenuSubItem(String subItemText) {
        homePage.EducationMenuOptions.findBy(matchText(subItemText)).click();
        return this;
    }

}
