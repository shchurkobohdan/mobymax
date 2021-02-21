package core.element;

import lombok.Getter;
import org.openqa.selenium.WebElement;

public class ElementHolder {
	@Getter
	private final WebElement webElement;

	public ElementHolder(WebElement element) {
		this.webElement = element;
	}

}
