package ui.sections.homepage;

import core.element.Element;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.elements.button.Button;
import ui.elements.dropdown.StateDropdown;
import ui.elements.input.SchoolInputWithSelection;
import ui.elements.input.TextInputField;
import ui.pages.MobyMaxCentralPage;

@Slf4j
public class RegisterSection extends Element {

	@FindBy(css = "#register-element-form input[name='data[FirstName]']")
	private TextInputField firstNameInput;
	@FindBy(css = "#register-element-form input[name='data[LastName]']")
	private TextInputField lastNameInput;
	@FindBy(css = "#register-element-form .signup-field-us-schools.signup-input-state")
	private StateDropdown stateDropdown;
	@FindBy(css = "#registration .signup-field-us-schools.signup-input-school")
	private SchoolInputWithSelection findSchoolInput;
	@FindBy(css = "#register-element-form input[name='data[Email]']")
	private TextInputField emailInput;
	@FindBy(css = "#register-element-form input[name='data[Password]']")
	private TextInputField passwordInput;
	@FindBy(className = "signup-submit")
	private Button registerButton;

	public RegisterSection(WebElement webElement) {
		super(webElement);
	}

	@Step("Set First name field '{value}'")
	public RegisterSection setFirstNameInput(String value) {
		log.info("Set First Name: {}", value);
		this.firstNameInput.clearAndType(value);
		return this;
	}

	@Step("Set Last name field '{value}'")
	public RegisterSection setLastNameInput(String value) {
		log.info("Set Last Name: {}", value);
		this.lastNameInput.clearAndType(value);
		return this;
	}

	@Step("Select State '{state}'")
	public RegisterSection selectState(String state) {
		log.info("Set State: {}", state);
		scrollIntoView();
		this.stateDropdown.select(state);
		return this;
	}

	@Step("Set School '{value}'")
	public RegisterSection setSchool(String value) {
		log.info("Set School: {}", value);
		scrollIntoView();
		this.findSchoolInput.clearAndType(value);
		return this;
	}

	@Step("Set email '{value}'")
	public RegisterSection setEmailInput(String value) {
		log.info("Set Email: {}", value);
		scrollIntoView();
		this.emailInput.clearAndType(value);
		return this;
	}

	@Step("Set password '{value}'")
	public RegisterSection setPasswordInput(String value) {
		log.info("Set Password: {}", value);
		scrollIntoView();
		this.passwordInput.clearAndType(value);
		return this;
	}

	@Step("Click Register button")
	public MobyMaxCentralPage clickRegisterButton() {
		log.info("Click Register button");
		scrollIntoView();
		this.registerButton.click();
		return new MobyMaxCentralPage();
	}
}
