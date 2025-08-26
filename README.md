# Mid-Project-tbcbank.ge
UI automation suite using Java + Selenide + TestNG

# TBC Test Automation Project

Automated UI test framework for **TBC Bank** website, built with **Java**, **Selenide**, and **TestNG**.  
Supports **desktop** and **mobile** test runs via TestNG parameterization.

---

## 🛠 Tech Stack
- Java 17+
- Selenide
- TestNG
- Maven
- WebDriverManager

---

## 📂 Project Structure
-----------------
Project Structure
-----------------
pom.xml
testng.xml
src
├── main
└── test
└── java
└── ge/tbc/testautomation
├── runners   # BaseTest, DriverFactory
├── steps     # Step definitions (HomeSteps, AcademySteps, etc.)
├── tests     # Test classes (AcademyCoursesTest, MapFilterTest, etc.)
└── utils     # Utility classes


---

## ▶ Running Tests

### Desktop Suite
Run all tests in **desktop mode**:
```bash
mvn clean test -DsuiteXmlFile=src/test/resources/testng-desktop.xml
```
### Mobile Suite
```bash
Run tests in mobile emulation mode:

mvn clean test -DsuiteXmlFile=src/test/resources/testng-mobile.xml
```
ℹ Note: MapFilterTest is excluded in mobile mode.

### ⚙ TestNG Suites
testng-desktop.xml
```
<suite name="DesktopSuite">
    <test name="DesktopTests">
        <parameter name="mobile" value="false"/>
        <packages>
            <package name="ge.tbc.testautomation.tests"/>
        </packages>
    </test>
</suite>
```

testng-mobile.xml
```
<suite name="MobileSuite">
    <test name="MobileTests">
        <parameter name="mobile" value="true"/>
        <packages>
            <package name="ge.tbc.testautomation.tests">
                <exclude name="ge.tbc.testautomation.tests.MapFilterTest"/>
            </package>
        </packages>
    </test>
</suite>
```
### ✅ Features

* Page navigation & flows written as **steps** (`HomeSteps`, `AcademySteps`).
* Supports **conditional test flows** (desktop vs mobile).
* Centralized **driver management** in `DriverFactory`.
* Handles optional cookies popup (desktop only).
* Flexible suite configuration with TestNG.


### 👩‍💻 Author

Nino Shubitidze

Test Automation Project – TBC IT Academy