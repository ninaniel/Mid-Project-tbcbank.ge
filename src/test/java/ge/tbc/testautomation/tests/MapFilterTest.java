package ge.tbc.testautomation.tests;

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
                .filterByCity("ბათუმი")
                .verifyHalfOfMarkersInCity("Batumi")
                .searchStreet("ბაგრატიონი")
                .verifyBranchesAndPinsAreSameCount()
                .verifyEachBranchHasRelatedPin()
                .verifyBranchesStreetMatchSearch("ბაგრატიონი")
                .verifyPinsStreetMatchSearch("bagrationi");
    }
}
