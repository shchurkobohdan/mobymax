package ui.sections.signin_page;

import core.element.Element;
import io.qameta.allure.Step;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import ui.elements.button.Button;
import ui.pages.SignInPage;

import static org.openqa.selenium.By.className;

@Slf4j
public class HeaderMenu extends Element {

	public HeaderMenu(WebElement webElement) {
		super(webElement);
	}

	@Step("Select '{item}' tab")
	public SignInPage selectTab(MenuItem item) {
		log.info("Select {} tab", item.name());
		new Button(webDriver.findElement(className(item.getClassName()))).click();
		return new SignInPage();
	}

	public static enum MenuItem {
		AS_A_STUDENT("item-S"),
		AS_A_TEACHER("item-T"),
		AS_A_PARENT("item-P"),
		AS_A_SCHOOL_ADMIN("item-SA"),
		AS_A_DISTRICT_ADMIN("item-D");

		@Getter
		private String className;

		MenuItem(String className) {
			this.className = className;
		}
	}
}
