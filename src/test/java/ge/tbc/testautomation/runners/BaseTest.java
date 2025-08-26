package ge.tbc.testautomation.runners;

import com.codeborne.selenide.WebDriverRunner;
import ge.tbc.testautomation.utils.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    @Parameters({"mobile"})
    public void setUp(@Optional("false") boolean mobile) {
        driver = DriverFactory.createDriver(mobile);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            WebDriverRunner.closeWebDriver();
        }
    }
}