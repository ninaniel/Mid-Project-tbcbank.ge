package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.data.Constants;
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
        if (mobile) {
            homeSteps
                    .openBurgerMenu()
                    .openTbcSectionMobile(Constants.TBC_SECTION)
                    .openMegaMenuSubItem(Constants.SUB_ITEM_EDUCATION)
                    .openMegaMenuSubItem(Constants.SUB_ITEM_ACADEMY);
        } else {
            homeSteps.navigateToEducationDesktop();
            academySteps.openAcademyCardDesctop(Constants.SUB_ITEM_ACADEMY);
        }

        academySteps.validateCourses();
    }
}
