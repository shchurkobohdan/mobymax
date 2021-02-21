package ui.elements.input;

import core.element.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import util.WaitFor;

import java.util.List;

public class SchoolInputWithSelection extends Element {

	@FindBy(css = "[name='data[School]']")
	private TextInputField inputField;
	@FindBy(css = ".school-item, .school-not-listed")
	private List<WebElement> selectOptions;

	public SchoolInputWithSelection(WebElement webElement) {
		super(webElement);
	}

	public SchoolInputWithSelection clearAndType(String text) {
		inputField.clearAndType(text);
		WaitFor.presence(getSelectBodyLocator());
		refreshInnerElements();
		selectOptions.stream()
				.filter(option -> option.getText().equals(text) || option.getText().contains(text))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException(String.format("Failed to find option %s", text)))
				.click();
		WaitFor.invisibilityOf(getSelectBodyLocator());
		return this;
	}

	private By getSelectBodyLocator() {
		return By.className("school-select");
	}
}
