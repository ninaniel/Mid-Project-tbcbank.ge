package ge.tbc.testautomation.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class AcademyPage {

//    public ElementsCollection navigationItems() {
//        return $$(".tbcx-pw-navigation-item button");
//    }
//
//    public SelenideElement educationLink() {
//        return $(".ng-star-inserted a[href='/ka/tbc-education']");
//    }

    public ElementsCollection academyCards() {
        return $$(".pane-container .tbcx-pw-carousel__slides__slide__card");
    }

    public ElementsCollection academyCourses() {
        return $$(".tbcx-pw-carousel__slides-wrapper .tbcx-pw-carousel__card");
    }

    public SelenideElement courseTitle() {
        return $("h2.app-textpage-header__title");
    }

    public SelenideElement courseDescriptionH1() {
        return $("h1");
    }

    public ElementsCollection featureGridItems() {
        return $$(".tbcx-pw-feature-grid .tbcx-pw-feature-grid__item-info");
    }
}
