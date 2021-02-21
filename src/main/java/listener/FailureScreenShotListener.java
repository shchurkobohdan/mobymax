package listener;

import driver.WebDriverFactory;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Slf4j
public class FailureScreenShotListener extends TestListenerAdapter {

	@Override
	public void onTestFailure(ITestResult result) {
		makeScreenshotOnFinish(WebDriverFactory.getDriver());
	}

	@Step("Screenshot attachment")
	public void makeScreenshotOnFinish(WebDriver driver) {
		getScreenshot(driver);
	}

	@Attachment(value = "Screenshot after test", type = "image/png")
	public byte[] getScreenshot(WebDriver driver) {
		log.info("Taking the screenshot on test failure");

		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		byte[] screen = null;
		try {
			screen = IOUtils.toByteArray(new FileInputStream(screenshot));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return screen;
	}
}
