package ge.tbc.testautomation.runners;

import com.codeborne.selenide.WebDriverRunner;
import ge.tbc.testautomation.steps.HomeSteps;
import ge.tbc.testautomation.utils.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class BaseTest {

    protected WebDriver driver;
    protected HomeSteps homeSteps;

    @BeforeClass
    @Parameters({"mobile"})
    public void setUp(@Optional("false") boolean mobile) {
        driver = DriverFactory.createDriver(mobile);
        WebDriverRunner.setWebDriver(driver);
        driver.manage().window().maximize();

        homeSteps = new HomeSteps();

        if (mobile) {
            homeSteps.openHomePage();  // no cookies popup on mobile
        } else {
            homeSteps
                    .openHomePage()
                    .acceptCookiesIfPresent(); // only for desktop
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            WebDriverRunner.closeWebDriver();
        }
    }
}