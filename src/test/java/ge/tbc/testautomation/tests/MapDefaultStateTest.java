package ge.tbc.testautomation.tests;

import com.codeborne.selenide.*;
import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.runners.BaseTest;
import ge.tbc.testautomation.steps.HomeSteps;
import ge.tbc.testautomation.steps.LocationsSteps;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Map;


public class MapDefaultStateTest extends BaseTest {

    HomeSteps homeSteps = new HomeSteps();
    LocationsSteps locationsSteps = new LocationsSteps();

    @BeforeMethod
    public void setGeoLoc(){
        ChromeOptions chromeOptions = new ChromeOptions();
        Map<String, Object> prefs = Map.of(
                "profile.default_content_setting_values.geolocation", 2 // 2 = block
        );
        chromeOptions.setExperimentalOption("prefs", prefs);
    }

    @Test
    public void shouldFallbackToTbilisiWhenGeolocationBlocked() {
        homeSteps
                .navigateToLocations();

        locationsSteps
                .waitForMap()
                .verifyDefaultLocation(Constants.DEFAULT_CITY);
    }

}

