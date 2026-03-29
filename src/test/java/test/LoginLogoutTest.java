package test;

import org.testng.Assert;

import org.testng.annotations.Test;
import pages.LoginPage;
import pages.SecureAreaPage;

public class LoginLogoutTest extends BaseTest {

    @Test
    public void verifyLoginLogoutFlow() {

        LoginPage login = new LoginPage(driver);
        SecureAreaPage secure = new SecureAreaPage(driver);

        login.login(prop.getProperty("username"),prop.getProperty("password"));

        Assert.assertTrue(secure.isLoginSuccessful());

        secure.logout();
    }
}
