package ge.tbc.testautomation.utils;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {

    public static WebDriver createDriver(boolean mobileEmulation) {
        ChromeOptions options = new ChromeOptions();
        if (mobileEmulation) {
            options.addArguments("--window-size=375,667"); // mobile emulation
        } else {
            options.addArguments("--start-maximized");
        }
        WebDriver driver = new ChromeDriver(options);
        WebDriverRunner.setWebDriver(driver);
        return driver;
    }
}