package ge.tbc.testautomation.steps;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ge.tbc.testautomation.pages.LocationsPage;
import ge.tbc.testautomation.utils.MapUtil;
import org.testng.Assert;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;


public class LocationsSteps {

    LocationsPage locationsPage = new LocationsPage();

    public LocationsSteps waitForMap() {
        locationsPage.mapWrapper().shouldBe(visible);
        return this;
    }


    public LocationsSteps filterByCity(String city) {
        locationsPage.filterDropdown().click();
        locationsPage.filterOptions().findBy(text(city)).click();
        return this;
    }

    public LocationsSteps searchStreet(String street) {
        locationsPage.searchInput().setValue(street);
        return this;
    }

    public LocationsSteps verifyHalfOfMarkersInCity(String city) {
        ElementsCollection markers = locationsPage.markers().shouldBe(sizeGreaterThan(0), Duration.ofSeconds(15));

        int half = Math.max(1, markers.size() / 2);
        boolean allInCity = markers.stream()
                .limit(half)
                .allMatch(m -> {
                    String coordinates = m.getAttribute("position");
                    return MapUtil.isCoordinateInExpectedCity(coordinates, city);
                });

        Assert.assertTrue(allInCity);
        return this;
    }

    public LocationsSteps verifyBranchesAndPinsAreSameCount() {
        ElementsCollection results = locationsPage.branchListItems().shouldBe(sizeGreaterThan(0), Duration.ofSeconds(10));
        ElementsCollection markers = locationsPage.markers().shouldBe(sizeGreaterThan(0), Duration.ofSeconds(10));
        Assert.assertEquals(results.size(), markers.size());
        return this;
    }


    public LocationsSteps verifyBranchesStreetMatchSearch(String street) {
        ElementsCollection results = locationsPage.branchListItems().shouldBe(sizeGreaterThan(0), Duration.ofSeconds(10));

        for (int i = 0; i < results.size(); i++) {
            SelenideElement result = results.get(i);
            String branchAddress = result.getText();
            Assert.assertTrue(branchAddress.toLowerCase().contains(street));
        }

        return this;
    }

    public LocationsSteps verifyEachBranchHasRelatedPin() {
        ElementsCollection results = locationsPage.branchListItems().shouldBe(sizeGreaterThan(0), Duration.ofSeconds(10));

        for (int i = 0; i < results.size(); i++) {
            SelenideElement result = results.get(i);
            result.click();
            result.$(locationsPage.selectedBranch).shouldHave(cssClass("active"));
            locationsPage.relatedPin.shouldBe(visible);
        }
        return this;
    }

    public LocationsSteps verifyPinsStreetMatchSearch(String street) {
        ElementsCollection markers = locationsPage.markers().shouldBe(sizeGreaterThan(0), Duration.ofSeconds(10));

        for (SelenideElement m: markers) {
            String coordinates = m.getAttribute("position");
            String pinRoad = MapUtil.getRoad(coordinates);
            Assert.assertTrue(pinRoad.toLowerCase().contains(street));
        }
        return this;
    }

}