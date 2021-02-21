package core;

import core.element.FieldDecorator;
import org.openqa.selenium.support.PageFactory;

public abstract class Page extends PageObjectBase {

	public Page() {
		initElements();
		waitToLoad();
	}

	protected void initElements() {
		PageFactory.initElements(new FieldDecorator(webDriver), this);
	}

	protected void waitToLoad() {
	}

}
