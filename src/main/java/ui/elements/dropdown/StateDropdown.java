package ui.elements.dropdown;

import core.element.Element;
import driver.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import util.WaitFor;

import java.util.List;

public class StateDropdown extends Element implements Dropdown {

	private By dropdownBodyLocator = By.className("state-select");
	@FindBy(className = "state-item")
	private List<WebElement> dropdownOptions;

	public StateDropdown(WebElement webElement) {
		super(webElement);
	}

	@Override
	public List<String> getOptions() {
		return null;
	}

	@Override
	public String getSelectedOption() {
		return null;
	}

	@Override
	public Dropdown select(String option) {
		expandOptions();
		dropdownOptions.stream()
				.filter(opt -> opt.getText().equals(option))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Failed to find dropdown option"))
				.click();
		WaitFor.invisibilityOf(dropdownBodyLocator);
		return this;
	}

	@Override
	public boolean isExpanded() {
		return false;
	}

	@Override
	public boolean isEditable() {
		return false;
	}

	@Override
	public Dropdown expandOptions() {
		getWebElement().click();
		WaitFor.presence(dropdownBodyLocator);
		return this;
	}

	@Override
	public Dropdown collapseOptions() {
		return null;
	}
}
