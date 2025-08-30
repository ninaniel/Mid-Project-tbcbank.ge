package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.runners.BaseTest;
import ge.tbc.testautomation.steps.HomeSteps;
import ge.tbc.testautomation.steps.LocationsSteps;
import org.testng.annotations.Test;

public class MapFilterTest extends BaseTest {

    HomeSteps homeSteps = new HomeSteps();
    LocationsSteps locationsSteps = new LocationsSteps();

    @Test
    public void filterBatumiTest() {
        homeSteps
                .openHomePage()
                .acceptCookiesIfPresent()
                .navigateToLocations();

        locationsSteps
                .waitForMap()
                .filterByCity(Constants.CITY_TO_FILTER)
                .verifyHalfOfMarkersInCity(Constants.CITY_TO_CHECK_API)
                .searchStreet(Constants.STREET_TO_SEARCH)
                .verifyBranchesAndPinsAreSameCount()
                .verifyEachBranchHasRelatedPin()
                .verifyBranchesStreetMatchSearch(Constants.STREET_TO_SEARCH)
                .verifyPinsStreetMatchSearch(Constants.STREET_TO_CHECK_API);
    }
}
