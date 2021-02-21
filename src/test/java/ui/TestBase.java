package ui;

import driver.WebDriverFactory;
import io.qameta.allure.Step;
import listener.FailureScreenShotListener;
import org.testng.annotations.*;
import ui.pages.HomePage;

@Listeners({FailureScreenShotListener.class})
public class TestBase {
	protected HomePage homePage;

	@Parameters({"browser"})
	@BeforeSuite
	@Step("Set browser name")
	public void setProperty(@Optional("chrome") String browser) {
		System.setProperty("browser", browser);
	}

	@BeforeMethod
	public void setUp() {
		homePage = new HomePage();
	}

	@AfterMethod
	@Step("Close browser")
	public void tearDown() {
		WebDriverFactory.closeWebDriverInstance();
	}
}
