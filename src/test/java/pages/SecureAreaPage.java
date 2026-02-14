package pages;

import org.openqa.selenium.*;

public class SecureAreaPage {

    WebDriver driver;

    By successMsg = By.className("post-title");
    By logout = By.linkText("Log out");

    public SecureAreaPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isLoginSuccessful() {
        return driver.findElement(successMsg)
                .getText().contains("Logged In Successfully");
    }

    public void logout() {
        driver.findElement(logout).click();
    }
}
