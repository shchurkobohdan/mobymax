package ui.elements.input;

import core.element.Element;
import org.openqa.selenium.WebElement;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class TextInputField extends Element {

	public TextInputField(WebElement webElement) {
		super(webElement);
	}

	public TextInputField clear() {
		wait.until(visibilityOf(getWebElement())).clear();
		return this;
	}

	public TextInputField type(String text) {
		wait.until(visibilityOf(getWebElement())).sendKeys(text);
		return this;
	}

	public TextInputField clearAndType(String text) {
		clear();
		type(text);
		return this;
	}
}
