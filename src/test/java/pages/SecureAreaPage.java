package pages;

import org.openqa.selenium.*;

public class SecureAreaPage extends BasePage{

	 public SecureAreaPage(WebDriver driver) {
	        super(driver);
	    }

    By successMsg = By.className("post-title");
    By logout = By.linkText("Log out");

   

    public boolean isLoginSuccessful() {
        return driver.findElement(successMsg)
                .getText().contains("Logged In Successfully");
    }

    public void logout() {
        driver.findElement(logout).click();
    }
}
