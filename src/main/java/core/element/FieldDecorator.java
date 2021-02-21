package core.element;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.Field;
import java.util.List;

public class FieldDecorator extends DefaultFieldDecorator {

	public FieldDecorator(SearchContext context) {
		super(new DefaultElementLocatorFactory(context));
	}

	@Override
	public Object decorate(ClassLoader classLoader, Field field) {
		ElementLocator locator = factory.createLocator(field);
		String typeName = field.getGenericType().getTypeName();
		if (Element.class.isAssignableFrom(field.getType())) {
			return new ElementFactory()
					.create((Class<? extends Element>) field.getType(), proxyForLocator(classLoader, locator));
		} else if (List.class.isAssignableFrom(field.getType()) && !typeName.contains("WebElement")) {
			String cls = typeName.replaceAll("java.util.List<", "").replaceAll(">", "");
			return new ElementFactory()
					.create(cls, proxyForListLocator(classLoader, locator));
		}
		return super.decorate(classLoader, field);
	}
}
