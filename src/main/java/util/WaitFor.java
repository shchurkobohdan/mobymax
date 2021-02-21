package util;

import core.element.Element;
import driver.WebDriverFactory;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;
import java.util.function.BooleanSupplier;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

@Slf4j
public class WaitFor {

	public static WebElement presence(final By locator) {
		return new WebDriverWait(WebDriverFactory.getDriver(), 30)
				.until(presenceOfElementLocated(locator));
	}

	public static void visibilityOf(Element element) {
		int[] throttle = {1, 1, 1, 1, 2, 4, 4, 5, 5};
		for (int seconds : throttle) {
			try {
				if (element.isDisplayed()) {
					return;
				}
				WaitFor.seconds(seconds);
			} catch (NoSuchElementException | StaleElementReferenceException ex) {
				return;
			}
		}

		throw new TimeoutException("Element didn't disappear after 30 seconds of wait");
	}

	public static void invisibilityOf(Element element) {
		int[] throttle = {1, 1, 1, 1, 2, 4, 4, 5, 5};
		for (int seconds : throttle) {
			try {
				if (!element.isDisplayed()) {
					return;
				}
				WaitFor.seconds(seconds);
			} catch (NoSuchElementException | StaleElementReferenceException ex) {
				return;
			}
		}
	}

	public static void invisibilityOf(By elementLocator) {
		WebDriver webDriver = WebDriverFactory.getDriver();
		try {
			try {
				new WebDriverWait(webDriver, 1)
						.ignoring(Exception.class)
						.until(visibilityOfElementLocated(elementLocator));
			} catch (Exception ignored) {
			}
			new WebDriverWait(webDriver, 60).until(invisibilityOfElementLocated(elementLocator));
		} catch (Exception e) {
			throw new TimeoutException("Element is still visible " + elementLocator, e);
		}
	}

	public static void condition(BooleanSupplier condition, int timeOutSeconds, String errorMessage) {
		int[] throttle = {timeOutSeconds, 0};
		for (int seconds : throttle) {
			try {
				if (condition.getAsBoolean()) {
					return;
				}
			} catch (Exception ignored) {
			}
			WaitFor.seconds(seconds);
		}
		throw new TimeoutException(errorMessage);
	}

	public static void seconds(int i) {
		log.info("Wait '{}' seconds", i);
		try {
			TimeUnit.SECONDS.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
