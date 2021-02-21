package ui.elements.button;

import core.element.Element;
import org.openqa.selenium.WebElement;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class Button extends Element {

	public Button(WebElement webElement) {
		super(webElement);
	}

	public void click() {
		try {
			wait.until(elementToBeClickable(getWebElement())).click();
		} catch (Exception e) {
			if (e.getMessage().contains("Other element would receive the click")) {
				scrollIntoView();
				getWebElement().click();
			} else {
				throw e;
			}
		}
	}
}
