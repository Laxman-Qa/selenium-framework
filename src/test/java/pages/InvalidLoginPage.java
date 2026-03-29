package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InvalidLoginPage extends BasePage{

	public InvalidLoginPage(WebDriver driver) {
		super(driver);
		
	}
	
	private By username = By.id("username");
    private By password = By.id("password");
    private By submit = By.id("submit");
    private By error = By.id("error");

    public void invalidLogin(String user, String pass) {
        driver.findElement(username).sendKeys(user);
        driver.findElement(password).sendKeys(pass);
        driver.findElement(submit).click();
    }
    
    public String getErrorMessage() {
    	
    	return driver.findElement(error).getText();
    	
    }

}
