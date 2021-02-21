package core;

import driver.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class PageObjectBase {
	protected final WebDriver webDriver;
	protected final WebDriverWait wait;

	public PageObjectBase() {
		this.webDriver = WebDriverFactory.getDriver();
		this.wait = new WebDriverWait(this.webDriver, 30);
	}
}
