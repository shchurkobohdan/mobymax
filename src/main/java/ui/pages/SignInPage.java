package ui.pages;

import core.Page;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.support.FindBy;
import ui.elements.button.Button;
import ui.elements.input.TextInputField;
import ui.sections.signin_page.HeaderMenu;
import util.WaitFor;

import static org.openqa.selenium.By.className;

@Slf4j
public class SignInPage extends Page {

	@FindBy(className = "header-menu")
	private HeaderMenu header;
	@FindBy(css = "[name='data[User][Username]']")
	private TextInputField emailInput;
	@FindBy(css = "[name='data[User][Password]']")
	private TextInputField passwordInput;
	@FindBy(css = ".centralContent .signin-form-button")
	private Button signInButton;

	public HeaderMenu headerMenu() {
		return header;
	}

	@Step("Set email '{email}'")
	public SignInPage setEmail(String email) {
		log.info("Set Email: {}", email);
		emailInput.clearAndType(email);
		return this;
	}

	@Step("Set password '{password}'")
	public SignInPage setPassword(String password) {
		log.info("Set Password: {}", password);
		passwordInput.clearAndType(password);
		return this;
	}

	@Step("Click SignIn button")
	public MobyMaxCentralPage clickSignIn() {
		log.info("Click SignIn button");
		signInButton.click();
		return new MobyMaxCentralPage();
	}

	@Override
	protected void waitToLoad() {
		WaitFor.presence(className("header-sign-in-text"));
	}
}
