package ui.registration;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import ui.TestBase;
import ui.pages.MobyMaxCentralPage;

import static ui.sections.signin_page.HeaderMenu.MenuItem.AS_A_TEACHER;
import static util.Constants.PASSWORD;

@Feature("Register the new user as a Teacher and verify if login is possible")
public class TeacherRegistrationTest extends TestBase {
	private String email;

	@Test
	@Description("Verify new user registration as a teacher")
	public void testNewTeacherRegistration() {
		email = RandomStringUtils.randomAlphabetic(6) + "@wtfqa.us";
		MobyMaxCentralPage mobyMaxCentralPage = homePage.registerSection()
				.setFirstNameInput("TestName")
				.setLastNameInput("TestLastName")
				.selectState("California")
				.setSchool("QA Test School")
				.setEmailInput(email)
				.setPasswordInput(PASSWORD)
				.clickRegisterButton();

		Assert.assertTrue(mobyMaxCentralPage.isLoaded(), "MobyMax Central page is not opened");
	}

	@Test(dependsOnMethods = {"testNewTeacherRegistration"})
	@Description("Verify that new teacher can login")
	public void testNewTeacherLogin() {
		MobyMaxCentralPage mobyMaxCentralPage = homePage.header()
				.clickSignIn()
				.headerMenu()
				.selectTab(AS_A_TEACHER)
				.setEmail(email)
				.setPassword(PASSWORD)
				.clickSignIn();

		Assert.assertTrue(mobyMaxCentralPage.isLoaded(), "MobyMax Central page is not opened");
	}
}
