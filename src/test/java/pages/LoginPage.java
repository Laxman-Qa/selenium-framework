package pages;

import org.openqa.selenium.*;

public class LoginPage extends BasePage{

	public LoginPage(WebDriver driver) {
        super(driver);
    }

    private By username = By.id("username");
    private By password = By.id("password");
    private By submit = By.id("submit");

    

    public void login(String user, String pass) {
        driver.findElement(username).sendKeys(user);
        driver.findElement(password).sendKeys(pass);
        driver.findElement(submit).click();
    }
}
