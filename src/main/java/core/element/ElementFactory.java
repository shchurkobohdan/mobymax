package core.element;

import com.google.common.collect.Lists;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ElementFactory {

	public <E extends Element> E create(final Class<E> elementClass, final WebElement webElement) {
		E element = null;
		try {
			element = elementClass.getConstructor(WebElement.class).newInstance(webElement);
			element.refreshInnerElements();

		} catch (Exception ignored) {
		}
		return element;
	}

	public <E extends Element> ArrayList<E> create(String elementClass, final List<WebElement> webElements) {
		ArrayList<E> elements = Lists.newArrayList();
		try {
			for (WebElement webElement : webElements) {
				E element = (E) Class.forName(elementClass)
						.getConstructor(WebElement.class)
						.newInstance(webElement);
				element.refreshInnerElements();
				elements.add(element);
			}
		} catch (Exception ignored) {
		}
		return elements;
	}
}
