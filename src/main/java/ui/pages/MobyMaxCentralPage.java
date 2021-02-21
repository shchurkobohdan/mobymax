package ui.pages;

import core.Page;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import ui.sections.mobymax_central_page.HeaderBar;
import ui.sections.mobymax_central_page.LeftSideMenu;
import util.WaitFor;

public class MobyMaxCentralPage extends Page {

	@FindBy(css = "[class*='header--TitleBarContainer']")
	private HeaderBar header;
	@FindBy(css = "[class*='side-menu--SideMenuDrawer']")
	private LeftSideMenu leftMenu;

	@Step("Verify if MobyMax Central page is loaded")
	public boolean isLoaded() {
		return header.isDisplayed() && leftMenu.isDisplayed();
	}

	@Override
	protected void waitToLoad() {
		WaitFor.visibilityOf(leftMenu);
	}
}
