package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.runners.BaseTest;
import ge.tbc.testautomation.steps.AcademySteps;
import ge.tbc.testautomation.steps.HomeSteps;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class AcademyCoursesTest extends BaseTest {

    HomeSteps homeSteps = new HomeSteps();
    AcademySteps academySteps = new AcademySteps();

    @Test
    @Parameters({"mobile"})
    public void coursesTest(@Optional("false") boolean mobile) {

        homeSteps.openHomePage()
                .acceptCookiesIfPresent();

        if (mobile) {
            homeSteps.openBurgerMenu()
                    .openTbcSectionMobile("თიბისი")
                    .openMegaMenuSubItem("განათლება")
                    .openMegaMenuSubItem("IT აკადემია");
        } else {
            homeSteps.navigateToEducationDesktop();
            academySteps.openAcademyCardDesctop("IT აკადემია");
        }

        academySteps.validateCourses();
    }
}
