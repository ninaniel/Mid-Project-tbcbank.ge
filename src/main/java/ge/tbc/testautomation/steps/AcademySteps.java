package ge.tbc.testautomation.steps;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ge.tbc.testautomation.pages.AcademyPage;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class AcademySteps {

    AcademyPage academyPage = new AcademyPage();

//    public AcademySteps navigateToEducation() {
//        academyPage.navigationItems().findBy(matchText("თიბისი")).hover();
//        academyPage.educationLink().shouldBe(visible).click();
//        return this;
//    }

    public AcademySteps openAcademyCardDesctop(String cardTitle) {
        ElementsCollection cards = academyPage.academyCards().shouldBe(sizeGreaterThan(0), Duration.ofSeconds(10));
        cards.findBy(matchText(cardTitle)).shouldBe(visible).click();
        return this;
    }

    public AcademySteps validateCourses() {
        ElementsCollection courses = academyPage.academyCourses().shouldBe(sizeGreaterThan(0), Duration.ofSeconds(10));

        for (int i = 0; i < courses.size(); i++) {
            SelenideElement course = courses.get(i);
            if (course.is(visible)) {
                course.click();
                academyPage.courseTitle().shouldBe(visible);

                boolean h1Visible = academyPage.courseDescriptionH1().exists() &&
                        academyPage.courseDescriptionH1().has(text("კურსის აღწერა"));

                boolean featuresValid = false;
                ElementsCollection features = academyPage.featureGridItems();
                if (features.size() == 3) {
                    List<String> expected = List.of(
                            "შეხვედრების რაოდენობა",
                            "კურსის ხანგრძლივობა",
                            "ლექციების ფორმატი"
                    );
                    featuresValid = true;
                    for (int j = 0; j < expected.size(); j++) {
                        if (!features.get(j).has(exactText(expected.get(j)))) {
                            featuresValid = false;
                            break;
                        }
                    }
                }

                Assert.assertTrue(h1Visible || featuresValid,
                        "Expected either h1 containing 'კურსის აღწერა' or valid feature grid");

                back();
            }
        }
        return this;
    }
}
