package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.runners.BaseTest;
import ge.tbc.testautomation.steps.HomeSteps;
import ge.tbc.testautomation.steps.LocationsSteps;
import org.testng.annotations.Test;

public class MapDefaultStateTest extends BaseTest {

    HomeSteps homeSteps = new HomeSteps();
    LocationsSteps locationsSteps = new LocationsSteps();

    @Test
    public void shouldFallbackToTbilisiWhenGeolocationBlocked() {
        homeSteps
                .openHomePage()
                .acceptCookiesIfPresent()
                .navigateToLocations();

        locationsSteps
                .waitForMap()
                .verifyHalfOfMarkersInCity("Tbilisi");
    }
}

