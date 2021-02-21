package core.element;

import core.PageObjectBase;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public abstract class Element extends PageObjectBase {
	private ElementHolder elementHolder;

	public Element(WebElement webElement) {
		this.elementHolder = new ElementHolder(webElement);
	}

	public WebElement getWebElement() {
		return elementHolder.getWebElement();
	}

	public boolean isDisplayed() {
		try {
			return getWebElement().isDisplayed();
		} catch (Exception ignored) {
			return false;
		}
	}

	public boolean isEnabled() {
		try {
			return getWebElement().isEnabled();
		} catch (Exception ignored) {
			return false;
		}
	}

	public ExpectedCondition<WebElement> isReady() {
		return visibilityOf(getWebElement());
	}

	public boolean isClickable() {
		return isDisplayed() && isEnabled();
	}

	public String getAttribute(String attributeName) {
		return getWebElement().getAttribute(attributeName);
	}

	public void scrollIntoView() {
		wait.until(isReady());
		scrollIntoView(getWebElement());
	}

	protected void scrollIntoView(WebElement webElement) {
		String script = "arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});";
		((JavascriptExecutor) webDriver).executeScript(script, webElement);
	}

	public void refreshInnerElements() {
		PageFactory.initElements(new FieldDecorator(getWebElement()), this);
	}
}
