package test;

import pages.InvalidLoginPage;
import org.testng.Assert;

import org.testng.annotations.Test;
public class InvalidLoginTest extends BaseTest{

	@Test
	public void invalidLoginTest() {

		InvalidLoginPage invalidLogin = new InvalidLoginPage(driver);
		invalidLogin.invalidLogin(prop.getProperty("Iusername"),prop.getProperty("Ipassword"));
		String errorText = invalidLogin.getErrorMessage();
		Assert.assertEquals(errorText, "Your password is invalid!");
	}
}
