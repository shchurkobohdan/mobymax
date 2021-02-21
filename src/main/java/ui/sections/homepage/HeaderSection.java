package ui.sections.homepage;

import core.element.Element;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.elements.button.Button;
import ui.pages.SignInPage;

@Slf4j
public class HeaderSection extends Element {

	@FindBy(className = "btn-signin")
	private Button signInButton;

	public HeaderSection(WebElement webElement) {
		super(webElement);
	}

	@Step("Click SignIn button")
	public SignInPage clickSignIn(){
		log.info("Click SignIn button");
		signInButton.click();
		return new SignInPage();
	}
}
