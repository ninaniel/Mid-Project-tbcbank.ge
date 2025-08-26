package ge.tbc.testautomation.steps;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ge.tbc.testautomation.pages.LocationsPage;
import ge.tbc.testautomation.utils.MapUtil;
import org.testng.Assert;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class LocationsSteps {

    LocationsPage locationsPage = new LocationsPage();

    public LocationsSteps waitForMap() {
        locationsPage.mapWrapper().shouldBe(visible);
        return this;
    }

    public LocationsSteps verifyDefaultMapCenter(String city) {
        waitForMap();
        ElementsCollection markers = locationsPage.markers().shouldBe(sizeGreaterThan(0), Duration.ofSeconds(15));

        long inCityCount = markers.stream().filter(m -> isMarkerInCity(m, city)).count();
        Assert.assertTrue(inCityCount >= markers.size() / 2, "Map is not centered on " + city);
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

    public LocationsSteps verifyMarkersInCity(String city) {
        ElementsCollection markers = locationsPage.markers().shouldBe(sizeGreaterThan(0), Duration.ofSeconds(10));
        boolean allInCity = markers.stream().allMatch(m -> isMarkerInCity(m, city));
        Assert.assertTrue(allInCity, "Not all markers are in " + city);
        return this;
    }

    public LocationsSteps verifyBranchAddressesMatchMarkers() {
        ElementsCollection results = locationsPage.branchListItems().shouldBe(sizeGreaterThan(0), Duration.ofSeconds(10));

        for (int i = 0; i < results.size(); i++) {
            SelenideElement result = results.get(i);
            String fullAddress = result.getText();
            String expectedHouseNumber = fullAddress.split("#")[1].split("\n")[0].trim();
            result.click();
            results.get(i).$(".tbcx-pw-atm-branches-section__list-item").shouldHave(cssClass("active"));

            SelenideElement relatedPin = $("gmp-advanced-marker .active").shouldBe(visible);
            String position = relatedPin.closest("gmp-advanced-marker").getAttribute("position");
            String[] parts = position.split(",");
            String lat = parts[0];
            String lon = parts[1];

            String apiHouseNumber = MapUtil.getHouseNumber(lat, lon);
            String apiRoad = MapUtil.getRoad(lat, lon);

            if (!apiHouseNumber.isEmpty()) {
                Assert.assertEquals(apiHouseNumber, expectedHouseNumber, "House number mismatch for: " + fullAddress);
            }
            Assert.assertTrue(apiRoad.toLowerCase().contains("bagrationi"), "Road does not contain 'Bagrationi': " + apiRoad);
        }

        return this;
    }

    private boolean isMarkerInCity(SelenideElement marker, String city) {
        String position = marker.getAttribute("position");
        String[] coords = position.split(",");
        String lat = coords[0].trim();
        String lon = coords[1].trim();
        return MapUtil.isCoordinateInExpectedCity(lat, lon, city);
    }
}