package ui.elements.dropdown;

import java.util.List;

public interface Dropdown {

	List<String> getOptions();

	String getSelectedOption();

	Dropdown select(String option);

	boolean isExpanded();

	boolean isEditable();

	Dropdown expandOptions();

	Dropdown collapseOptions();
}
