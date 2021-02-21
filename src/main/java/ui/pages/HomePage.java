package ui.pages;

import core.Page;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.support.FindBy;
import ui.sections.homepage.HeaderSection;
import ui.sections.homepage.RegisterSection;
import util.WaitFor;

@Slf4j
public class HomePage extends Page {
	private final String URL = "https://www.mobymax.com/";

	@FindBy(id = "header")
	private HeaderSection header;
	@FindBy(id = "registration")
	private RegisterSection registerSection;

	public HeaderSection header() {
		return header;
	}

	public RegisterSection registerSection(){
		return registerSection;
	}

	@Step("Open Main page (MobyMax for educators)")
	@Override
	protected void waitToLoad() {
		log.info("Open {}", URL);
		webDriver.get(URL);
		WaitFor.visibilityOf(header);
	}
}
